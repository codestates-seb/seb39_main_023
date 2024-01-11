const PROXY = window.location.hostname === 'localhost' ? '' : '%PUBLIC_URL%';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { config } from '../../config/config';
import { useEffect, useState } from 'react';
import { ImgWrapper, FirstRow, SecondRow, ThirdRow, FourthRow } from './ListItem';
import { IPostItem } from '../../pages/Main/Main';
export interface PostItemData {
  category: string;
  image: any;
  location: string | undefined;
  rentPostContents: string | undefined;
  rentPostId: number | undefined;
  rentPostName: string | undefined;
  rentPostImages: number[] | undefined;
  rentPrice: number | undefined;
  rentStatus: boolean | undefined;
  updateDate: string | undefined;
  viewCount: number | undefined;
  writeDate: string | undefined;
  writerId: number | undefined;
  ref?: React.LegacyRef<HTMLDivElement> | undefined;
}

const PostItem = ({ postList }: { postList: IPostItem[] }) => {
  const navigate = useNavigate();
  const [targetImage, setTargetImage] = useState();
  // useEffect(() => {
  //   const getImages = async () => {
  //     const result = await axios.get(`${PROXY}/rentPost/images/get?postId=${data.rentPostId}`);
  //     setTargetImage(result.data[0]);
  //   };
  //   getImages();
  // }, []);
  // const imgUrl: string | undefined = targetImage && `${PROXY}/rentPost/image/get?imageId=${targetImage}`;

  // const price = data.rentPrice?.toLocaleString();
  // const location = data.location?.slice(8);
  // const createdAt = new Date(String(data.updateDate)).toLocaleDateString().slice(0, 13);
  // const processedDate = createdAt.slice(0, -1);
  const goDetailPage = () => {
    navigate(`/postdetail/1`);
  };

  return (
    <>
      {postList.map((post, idx) => (
        <ListWrapper key={idx}>
          <ImgWrapper onClick={goDetailPage}>
            <img src={`${PROXY}/book-modern-js.png`} />
          </ImgWrapper>
          <DescriptionWrapper>
            <FirstRow onClick={goDetailPage}>
              <span>{post.rentPostName}</span>
              <span className="location">{post.location}</span>
            </FirstRow>
            <SecondRow>
              <span>{post.writeDate}</span>
            </SecondRow>

            <ThirdRow>
              <Price>{post.rentPrice?.toLocaleString()} 원</Price>
            </ThirdRow>
            <FourthRow>
              <span id="tag" className="fourthRow">
                {post.rentStatus ? '렌트중' : '렌트가능'}
              </span>
              <span className="fourthRow">조회 {post.viewCount}</span>
            </FourthRow>
          </DescriptionWrapper>
        </ListWrapper>
      ))}
    </>
  );
};

const ListWrapper = styled.div`
  display: flex;
  padding-top: 10px;
  border-bottom: 1px solid #dee2e6;
  padding-bottom: 10px;
  padding-right: 10px;
  flex-basis: 33%;
  @media screen and (max-width: 500px) {
    width: 90%;
    margin-top: 5px;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-right: 0px;
  }
`;

const DescriptionWrapper = styled.div`
  width: 100%;
`;

const Title = styled.h2`
  font-size: 16px;
  letter-spacing: -0.02px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 4px;
  line-height: 1.5;
  font-weight: normal;
  margin: 5px auto;
`;

const Price = styled.div`
  font-size: 15px;
  font-weight: 700;
  line-height: 1.5;
  margin-bottom: 4px;
`;

const Region = styled.div`
  font-size: 13px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 4px;
  line-height: 1.5;
`;

export default PostItem;
