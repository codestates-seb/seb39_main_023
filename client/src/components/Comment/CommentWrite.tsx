import { config } from '../../config/config';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import { ChangeEvent, MouseEvent } from 'react';
import Button from '../../UI/button/Button';
import TextInput from '../../UI/input/TextInput';
import { getComments, sendComment } from '../../Utils/post';
import { UserContext } from '../../context/context';
import { useContext } from 'react';
const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
import { ReactComponent as guestImg } from '../../asessts/img/guestImg.svg';
import { useNavigate } from 'react-router-dom';
export interface CommentData {
  // writerId: number | undefined;
  writerId: number;
  targetPostId: number;
  commentContents: string;
  commentId: number;
  writeDate?: string;
}

export interface CommentWriteProps {
  postId: number;
  setRenewCommentsList: (renewComments: any) => void;
  renewComments: CommentData[];
}

const CommentWrite = ({ postId, setRenewCommentsList, renewComments }: CommentWriteProps) => {
  const { user } = useContext(UserContext);
  const navigate = useNavigate();
  const [comment, setComment] = useState<CommentData>({
    commentContents: '',
    targetPostId: postId,
    writerId: user.memberId,
    commentId: 0,
  });
  useEffect(() => {
    setComment({ ...comment, targetPostId: postId });
  }, [postId]);

  const onChangeComment = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setComment({ ...comment, [name]: value });
  };

  const clickHandler = () => {
    if (user.memberId) {
      comment.writeDate = String(new Date());
      setRenewCommentsList([comment, ...renewComments]);
      sendComment(comment);
      setComment({ ...comment, commentContents: '' });
    } else {
      goLoginPage();
    }
  };
  const goLoginPage = () => {
    navigate('/login');
  };
  const imgUrl = `${PROXY}/member/profileImage/get?memberId=${user.memberId}`;
  return (
    <>
      <CommentWrapper>
        {user.memberId ? <Image alt="practice" src={imgUrl} /> : <GuestSVG />}
        <TextInput
          type={'text'}
          placeholder={user.memberId ? '댓글을 입력하세요' : '댓글을 쓰려면 로그인이 필요합니다'}
          onChange={onChangeComment}
          value={comment.commentContents}
          name={'commentContents'}
          // onClick={user.memberId ? undefined : goLoginPage}
        />
        <Button text="작성" width="short" onClick={clickHandler} />
      </CommentWrapper>
    </>
  );
};
const CommentWrapper = styled.div`
  width: 1000px;
  border: 1px solid #e9ecef;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  button {
    margin-left: 5px;
    white-space: nowrap;
  }
  input {
    width: 800px;
    height: 33px;
    border-radius: 0px;
  }
  @media screen and (max-width: 500px) {
    width: 100%;
    input {
      width: 230px;
    }
  }
`;
const GuestSVG = styled(guestImg)`
  width: 60px;
  height: 60px;
`;
export const Image = styled.img`
  width: 2rem;
  height: 2rem;
  border-radius: 50%;
  margin-right: 0.5rem;
  padding-right: 0;
`;

export default CommentWrite;
