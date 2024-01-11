import { MyHeader, Top, TopLeft, LogoSVG, Icons, LogoWrapper, CategoryWrapper } from './style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faUser, faCirclePlus } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { useContext, useState } from 'react';
import MenuModal from '../Modal/MenuModal';
import UserModal from '../Modal/UserModal';
import SearchBar from '../Search/SearchBar';
import { UserContext } from '../../context/context';
import DesktopRightSide from './DesktopRightSide/DesktopRightSide';
const Header = () => {
  const { user } = useContext(UserContext);
  const navigate = useNavigate();
  const [menuModal, setMenuModal] = useState(false);
  const [userModal, setUserModal] = useState(false);
  const [keyword, setKeyword] = useState<string>('');

  return (
    <MyHeader>
      <Top>
        <TopLeft>
          <LogoWrapper onClick={() => navigate(`/`)}>
            <LogoSVG />
            <div className="title">빌리지뭐</div>
          </LogoWrapper>
          <CategoryWrapper menuModal={menuModal}>
            <div className="category" onClick={() => setMenuModal(true)}>
              카테고리
            </div>
          </CategoryWrapper>
        </TopLeft>
        <Icons>
          <FontAwesomeIcon
            className="icon"
            icon={faCirclePlus}
            onClick={() => navigate('/postwrite')}
            // onClick={user.memberId ? () => navigate('/postwrite') : () => navigate('/login')}
          />
          <FontAwesomeIcon icon={faBars} onClick={() => setMenuModal(true)} className="icon" />
          <FontAwesomeIcon
            className="icon"
            icon={faUser}
            onClick={() => setUserModal(true)}
            // onClick={user.memberId ? () => setUserModal(true) : () => navigate('/login')}
          />
        </Icons>
      </Top>
      <SearchBar keyword={keyword} setKeyword={setKeyword} />
      <DesktopRightSide setUserModal={setUserModal} memberId={user.memberId} />
      {menuModal ? <MenuModal menuModal={menuModal} setMenuModal={setMenuModal} /> : null}
      {userModal ? <UserModal userModal={userModal} setUserModal={setUserModal} /> : null}
      {/* {user.memberId && userModal ? <UserModal userModal={userModal} setUserModal={setUserModal} /> : null} */}
    </MyHeader>
  );
};
export default Header;
