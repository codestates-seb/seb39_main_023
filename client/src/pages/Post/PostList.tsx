import { getPosts } from '../../Utils';
import PostItem from '../../components/PostItem/PostItem';
import { useState, useEffect, useRef } from 'react';
import styled from 'styled-components';
import SearchFilter from '../../components/Search/SearchFilter';
import { sortOptionList } from '../../constants';
import { ItemContainer } from '../Main/Main';
import useIntersectionObserver from '../../hooks/useIntersectionObserver';
import useScroll from '../../hooks/useScroll';

const PostList = () => {
  const [posts, setPosts] = useState([]);
  const [sortType, setSortType] = useState('writeDate');
  const ref = useRef<HTMLDivElement | null>(null);
  const entry = useIntersectionObserver(ref, {});
  const isVisibled = !!entry?.isIntersecting;

  // console.log(`Render Section ${isVisibled}`);

  useScroll();
  useEffect(() => {
    getPosts(sortType)
      .then((res) => {
        setPosts(res.rentPosts);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [sortType]);

  const handleSortChange = (e: any) => {
    setSortType(e.target.value);
  };

  return (
    <>
      <Top>
        <HeadRow>
          <div>오늘은 뭐 빌리지?</div>
        </HeadRow>
        <SearchFilter value={sortType} onChange={handleSortChange} optionList={sortOptionList} />
      </Top>
      <ItemContainer>
        {posts.map((el, idx) => (
          <div key={idx}></div>
          // <PostItem data={el} key={el.rentPostId} />
        ))}
      </ItemContainer>
      <div ref={ref} />
    </>
  );
};

const HeadRow = styled.div`
  width: 80%;
  text-align: left;
  font-size: 22px;
  font-weight: 600;
`;
const Top = styled.div`
  display: flex;
  justify-content: space-between;
  width: 80%;
  @media screen and (max-width: 500px) {
    width: 90%;
  }
`;

export default PostList;
