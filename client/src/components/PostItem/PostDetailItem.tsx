const PROXY = window.location.hostname === 'localhost' ? '' : '%PUBLIC_URL%';
import styled from 'styled-components';
import Button from '../../UI/button/Button';
import TextButton from '../../UI/button/TextButton';
import { Link, useNavigate } from 'react-router-dom';
import { useContext, useEffect, useState } from 'react';
import DeleteModal from '../Modal/DeleteModal';
import { UserContext } from '../../context/context';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleRight, faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { config } from '../../config/config';
import axios from 'axios';
import { IPostItem } from '../../pages/Main/Main';
export interface PostItemDetailData {
  category: string;
  rentPostImages: number[];
  location: string;
  rentPostContents: string;
  rentPostId: number;
  rentPostName: string;
  rentPrice: number;
  rentStatus: boolean;
  updateDate: string;
  viewCount: number;
  writeDate: string;
  writerId: number;
  deleteModal: boolean;
  commentId: number;
  setDeleteModal: React.Dispatch<React.SetStateAction<boolean>>;
}

interface IPostItemDetailProps {
  data: {
    category: string;
    rentPostImages: number[];
    location: string;
    rentPostContents: string;
    rentPostId: number;
    rentPostName: string;
    rentPrice: number;
    rentStatus: boolean;
    updateDate: string;
    viewCount: number;
    writeDate: string;
    writerId: number;
    deleteModal: boolean;
    commentId: number;
    setDeleteModal: React.Dispatch<React.SetStateAction<boolean>>;
  };
}

const PostDetailItem = ({ data }: { data: IPostItem }) => {
  // const imgUrl: string = `${config.apiUrl}rentPost/image/get?imageId=${data.rentPostImages[0]}`;
  const [count, setCount] = useState(1);
  const [imageURL, setImageURL] = useState('');
  const { user } = useContext(UserContext);
  const writerImageUrl = `${PROXY}/member/profileImage/get?memberId=${data.writerId}`;
  const [deleteModal, setDeleteModal] = useState(false);
  const navigate = useNavigate();
  const price = data.rentPrice?.toLocaleString();
  const location = data.location?.slice(8);
  const category = data.category?.slice(8);
  const createdAt = new Date(String(data.updateDate)).toLocaleDateString().slice(0, 13);
  const processedDate = createdAt.slice(0, -1);
  const [nickname, setNickname] = useState('');
  useEffect(() => {
    const getWriterInfo = async () => {
      try {
        const res = await axios.get(`${PROXY}/member/profile?memberId=${data.writerId}`);
        setNickname(res.data.nickname);
      } catch {}
    };
    getWriterInfo();
    setImageURL(`${PROXY}/rentPost/image/get?imageId=${data.rentPostImages[0]}`);
  }, [data]);
  const editHandler = () => {
    navigate(`/postedit/${data.rentPostId}`);
  };
  //증가
  const increaseCount = () => {
    setCount(count + 1);
  };
  const getNextImage = () => {
    if (count >= data.rentPostImages.length - 1) {
      setCount(0);
    } else {
      increaseCount();
      setImageURL(`${config.apiUrl}rentPost/image/get?imageId=${data.rentPostImages[count]}`);
    }
  };
  //감소
  const decreaseCount = () => {
    setCount(count - 1);
  };
  const getPrevImage = () => {
    if (count <= 0) {
      setCount(data.rentPostImages.length);
    } else {
      decreaseCount();
      setImageURL(`${config.apiUrl}rentPost/image/get?imageId=${data.rentPostImages[count]}`);
    }
  };

  return (
    <>
      <ListWrapper>
        <ImgWrapper>
          {/* <FontAwesomeIcon icon={faAngleLeft} onClick={getPrevImage} className="icon" /> */}
          {/* <FontAwesomeIcon icon={faAngleRight} onClick={getNextImage} className="icon" /> */}
          <GoodsImage src={`${PROXY}/book-modern-js.png`} />
          {/* <GoodsImage src={imgUrl} /> */}
        </ImgWrapper>
        <DescriptionWrapper>
          <Title>{data.rentPostName}</Title>
          <InfoWrapper>
            <PostInfo>
              <span>{location}</span>
              <span>{category}</span>
            </PostInfo>
            <PostInfo>
              <span>{data.writeDate}</span>
              <span>조회 {data.viewCount}</span>
            </PostInfo>
          </InfoWrapper>
          <Price>{price}원</Price>
          <FourthRow>
            <Button text={data.rentStatus === false ? '렌트가능' : '렌트중'} radius="deep" width="short" />
            <WriterInfo>
              <ImgWrapper>
                <img src={`${PROXY}/user.png`} className="writerImage" />
                <span></span>
              </ImgWrapper>
              <div className="nickname">{nickname}</div>
            </WriterInfo>
          </FourthRow>
          <ButtonWrapper>
            <TextButtonWrapper>
              {user.memberId === data.writerId ? (
                <>
                  <Link className="editbutton" to={`/postedit/${data.rentPostId}`} state={{ data: data }}>
                    수정
                  </Link>
                  <TextButton isGray={true} btnText={'삭제'} onClick={() => setDeleteModal(true)} />
                </>
              ) : (
                <Button text="채팅하기" type="beige" width="short" />
              )}
            </TextButtonWrapper>
          </ButtonWrapper>
          <ContentWrapper>
            <Content>{data.rentPostContents}</Content>
          </ContentWrapper>
        </DescriptionWrapper>
      </ListWrapper>
      {/* {deleteModal ? <DeleteModal setDeleteModal={setDeleteModal} data={data} /> : null} */}
    </>
  );
};
const ImgWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  .icon {
    cursor: pointer;
  }
`;
const GoodsImage = styled.img`
  width: 300px;
  height: 300px;
`;
const ContentWrapper = styled.div`
  padding: 10px;
  margin-bottom: 10px;
  margin-top: 10px;
  border-top: 1px solid #ececec;
`;
const Content = styled.div`
  font-size: 15px;
  color: #464646;
`;
const InfoWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 0 10px;
  align-items: center;
`;
const WriterInfo = styled.div`
  display: flex;
  align-items: center;
  .writerImage {
    width: 40px;
    height: 40px;
    border-radius: 100%;
  }
  .nickname {
    font-size: 14px;
  }
`;
const PostInfo = styled.div`
  display: flex;
  color: #727272;
  span {
    margin-right: 3px;
    font-size: 14px;
  }
`;
const FourthRow = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 3px 10px;
  button {
    height: 28px;
  }
`;
const ButtonWrapper = styled.div`
  display: flex;
  justify-content: flex-end;
  padding-right: 10px;
  button {
    margin-left: 5px;
    font-size: 14px;
  }
`;

const TextButtonWrapper = styled.div`
  display: flex;
  justify-content: reverse-row;
  margin-top: 5px;
  .editbutton {
    text-decoration: none;
    font-size: 14px;
    color: #727272;
  }
`;

const ListWrapper = styled.div`
  display: flex;
  flex-direction: row;
  width: 1000px;
  min-height: 400px;
  margin: 0;
  padding: 0;
  object-fit: cover;
  @media screen and (max-width: 500px) {
    width: 100%;
    object-fit: cover;
    margin: 0;
    padding: 0;
    margin-bottom: 20px;
    display: flex;
    flex-direction: column;
  }
`;

const DescriptionWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
`;

const Title = styled.div`
  padding: 10px;
  font-size: 20px;
  font-weight: 500;
  line-height: 1.5;
  margin-bottom: 20px;

  @media screen and (max-width: 500px) {
    font-size: 18px;
  }
`;

const Price = styled.div`
  font-size: 20px;
  font-weight: 600;
  line-height: 1.5;
  padding: 0 10px;
`;

const Region = styled.div`
  display: flex;
  flex-direction: row-reverse;
  font-size: 13px;
  color: #868e96;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 4px;
  line-height: 1.5;
  padding-left: 300px;
  padding-right: 10px;
`;

export default PostDetailItem;
