import PostDetailItem from '../../components/PostItem/PostDetailItem';
import styled from 'styled-components';
import Button from '../../UI/button/Button';
import { useState, useEffect } from 'react';
import { getPost } from '../../Utils';
import { PostItemDetailData } from '../../components/PostItem/PostDetailItem';
import CommentList from '../../components/Comment/CommentList';
import CommentWrite from '../../components/Comment/CommentWrite';
import { useParams } from 'react-router-dom';
import { config } from '../../config/config';
import { CommentData } from '../../components/Comment/CommentWrite';
import { getComments } from '../../Utils';
import useScroll from '../../hooks/useScroll';
import { IPostItem } from '../Main/Main';

const PostDetail = () => {
  const params = useParams<{ id: string }>();
  const initialState = {
    category: '',
    rentPostImages: [0],
    location: '',
    rentPostContents: '',
    rentPostId: 0,
    rentPostName: '',
    rentPrice: 0,
    rentStatus: true,
    updateDate: '',
    viewCount: 0,
    writeDate: '',
    writerId: 0,
    deleteModal: false,
    commentId: 0,
    setDeleteModal: () => {},
  };
  const [post, setPost] = useState<PostItemDetailData>(initialState);
  useScroll();
  // useEffect(() => {
  //   getPost(Number(params.id))
  //     .then((res) => {
  //       setPost(res);
  //     })
  //     .catch((err) => {});
  // }, []);
  const compare = (a: any, b: any) => {
    const aDate = new Date(a.updateDate).getTime();
    const bDate = new Date(b.updateDate).getTime();
    return bDate - aDate;
  };
  const [comments, setComments] = useState<CommentData[]>([]);
  // useEffect(() => {
  //   getComments(post.rentPostId).then((res) => {
  //     const newList = res.comments;
  //     newList.sort(compare);

  //     setComments(newList);
  //   });
  // }, [post.rentPostId, setComments]);
  interface CommentData {
    // writerId: number | undefined;
    writerId: number;
    targetPostId: number;
    commentContents: string;
    commentId: number;
    writeDate?: string;
  }
  const fakeCommentList = [
    {
      writerId: 1,
      targetPostId: 1,
      commentContents: '저 1월 20일 부터 2월 20일까지 한달 빌려보고 싶은데 가능할까요?.',
      commentId: 2,
      writeDate: '2024년 1월 15일',
    },
  ];
  const fakePostItem: IPostItem = {
    category: '책',
    image: '',
    location: '대전',
    rentPostContents: '100 회독해서 다 외워버렸네요 ^^ 싸게 빌려드립니다~',
    rentPostId: 0,
    rentPostName: '모던 딥다이브 자바스크립트',
    rentPostImages: '',
    rentPrice: 3000,
    rentStatus: false,
    updateDate: '2024년 1월 11일',
    viewCount: 9,
    writeDate: '2024년 1월 11일',
    writerId: 0,
  };

  return (
    <ItemContainer>
      <PostDetailItem data={fakePostItem} />
      <CommentCount>댓글</CommentCount>
      <CommentWrite postId={post.rentPostId} setRenewCommentsList={setComments} renewComments={comments} />
      <CommentList comments={fakeCommentList} setRenewCommentsList={setComments} renewComments={comments} />
    </ItemContainer>
  );
};
const ItemContainer = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 1000px;
  @media screen and (max-width: 500px) {
    width: 95%;
  }
`;

const CommentCount = styled.div`
  width: 1000px;
  padding: 10px;
  font-size: 15px;
  line-height: 1.5;
  color: #464646;
  word-break: break-all;
  @media screen and (max-width: 500px) {
    width: 100%;
  }
`;

export default PostDetail;
