const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Button from '../../UI/button/Button';
import { IListItemData } from '../../types';
import { config } from '../../config/config';
import { useEffect, useState } from 'react';
interface Props {
  data: IListItemData;
  isMyPost?: boolean;
}
const ListItem = ({ data, isMyPost }: Props) => {
  const navigate = useNavigate();
  const [targetImage, setTargetImage] = useState();
  useEffect(() => {
    const getImages = async () => {
      const result = await axios.get(`${PROXY}/rentPost/images/get?postId=${data.rentPostId}`);
      setTargetImage(result.data[0]);
    };
    getImages();
  }, []);
  const imgUrl: string | undefined = targetImage && `${PROXY}/rentPost/image/get?imageId=${targetImage}`;

  const price = data.rentPrice?.toLocaleString();
  const location = data.location?.slice(8);
  const createdAt = new Date(String(data.updateDate)).toLocaleDateString().slice(0, 13);
  const processedDate = createdAt.slice(0, -1);
  const goDetailPage = () => {
    navigate(`/postdetail/${data.rentPostId}`);
  };
  return (
    <ListItemContainer key={data.rentPostId}>
      <ImgWrapper onClick={goDetailPage}>
        <img src={imgUrl} />
      </ImgWrapper>
      <ContentWrapper>
        <FirstRow onClick={goDetailPage}>{data.rentPostName}</FirstRow>
        <SecondRow>
          <span>{location}</span>
          <span>{processedDate}</span>
        </SecondRow>
        <ThirdRow>
          <div className="thirdRow">{price}원</div>
        </ThirdRow>
        <FourthRow>
          <span id="tag" className="fourthRow">
            {data.rentStatus ? '렌트중' : '렌트가능'}
          </span>
          {isMyPost ? (
            <Button text={'채팅목록'} width="short" type={'beige'} />
          ) : (
            <span className="fourthRow">조회 {data.viewCount}</span>
          )}
        </FourthRow>
      </ContentWrapper>
    </ListItemContainer>
  );
};

export default ListItem;
const ListItemContainer = styled.div`
  display: flex;
  flex-basis: 33%;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-right: 10px;
  border-bottom: #efeded 0.5px solid;
  @media screen and (max-width: 500px) {
    width: 100%;
    margin-top: 5px;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-right: 0px;
  }
`;
export const ImgWrapper = styled.div`
  display: flex;
  margin-right: 10px;
  cursor: pointer;
  img {
    width: 160px;
    height: 160px;
    border-radius: 5px;
  }
  @media screen and (max-width: 500px) {
    img {
      width: 90px;
      height: 90px;
    }
  }
`;

const ContentWrapper = styled.div`
  width: 100%;
`;

export const FirstRow = styled.div`
  font-size: 20px;
  font-weight: 500;
  cursor: pointer;
  min-height: 80px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3; /* 라인수 */
  -webkit-box-orient: vertical;
  word-wrap: break-word;
  line-height: 1.2em;
  height: 3.6em;
  .location {
    font-size: 14px;
    font-weight: 400;
    margin-left: 4px;
    color: gray;
  }
  @media screen and (max-width: 500px) {
    font-size: 15px;
    min-height: 20px;
  }
`;
export const SecondRow = styled.div`
  display: flex;
  align-items: center;
  font-size: 15px;
  color: #aba8a8;
  span {
    margin-right: 5px;
  }
  @media screen and (max-width: 500px) {
    font-size: 12px;
  }
`;

export const ThirdRow = styled.div`
  font-size: 20px;
  font-weight: 700;
  color: #464646;
  @media screen and (max-width: 500px) {
    font-size: 14px;
  }
`;
export const FourthRow = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 2px;
  font-size: 14px;
  color: #727272;
  #tag {
    background-color: #95d1cc;
    color: white;
    padding: 4px 12px;
    border-radius: 20px;
    font-size: 14px;
    font-weight: 500;
  }
  @media screen and (max-width: 500px) {
    font-size: 12px;
    #tag {
      padding: 3px 8px;
      font-size: 11px;
    }
    button {
      width: 50px;
      height: 25px;
    }
  }
`;
