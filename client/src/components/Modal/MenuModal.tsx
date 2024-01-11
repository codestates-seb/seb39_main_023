import { ModalBackDrop, Container, Top, Bottom, Item } from './style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faX } from '@fortawesome/free-solid-svg-icons';
import { category } from '../../constants';
import { useContext } from 'react';
import { SearchResultContext, UserContext } from '../../context/context';
import { useNavigate } from 'react-router-dom';
import { searchCategoryKeyword } from '../../Utils';

interface Props {
  menuModal?: boolean;
  setMenuModal?: (state: boolean) => void;
}
const MenuModal = ({ setMenuModal, menuModal }: Props) => {
  const { user } = useContext(UserContext);
  const navigate = useNavigate();
  const { setSearchResultList } = useContext(SearchResultContext);
  const modalOpen: boolean = menuModal ? true : false;
  const closeModal = () => {
    setMenuModal && setMenuModal(false);
  };

  const getCategoryPosts = async (e: any) => {
    if (e.target.innerText === '인기리스트' && !user.memberId) {
      alert('로그인이 필요합니다 :)');
      navigate('/login');
      return;
    } else if (e.target.innerText === '인기리스트' && user.memberId) {
      navigate('/');
      return;
    } else {
      const result = await searchCategoryKeyword(e.target.innerText);
      if (result) {
        try {
          setSearchResultList(result.data.rentPosts);
          navigate('/search/category', {
            state: {
              category: e.target.innerText,
              totalPages: result.data.totalPages,
              totalPostCount: result.data.totalEntity,
            },
          });
        } catch {
          alert('죄송합니다 잠시 후 다시 시도해주세요 :)');
        }
      }
    }
  };
  return (
    <ModalBackDrop onClick={closeModal}>
      <Container modalOpen={modalOpen}>
        <Top>
          <div className="title">카테고리</div>
          <FontAwesomeIcon icon={faX} onClick={closeModal} className="icon" />
        </Top>
        <Bottom modalOpen={modalOpen}>
          {category.map((el: any) => (
            <Item key={el.id}>
              <div onClick={(e) => getCategoryPosts(e)}>{el.name}</div>
            </Item>
          ))}
        </Bottom>
      </Container>
    </ModalBackDrop>
  );
};

export default MenuModal;
