import styled from 'styled-components';
import { Link } from 'react-router-dom';
import TextButton from '../../UI/button/TextButton';
import { CommentData } from './CommentWrite';
import { ChangeEvent, useContext, useEffect, useState } from 'react';
import { deleteComment, updateComment } from '../../Utils';
import { UserContext } from '../../context/context';
import TextInput from '../../UI/input/TextInput';
import { useNavigate } from 'react-router-dom';
import { config } from '../../config/config';
import axios from 'axios';
const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
export interface CommentDataProps {
  data: CommentData;
  setRenewCommentsList: (renewComments: any) => void;
  renewComments: CommentData[];
}

const CommentItem = ({ data, setRenewCommentsList, renewComments }: CommentDataProps) => {
  const { user } = useContext(UserContext);
  const [editComment, setEditComment] = useState(false);
  const [text, setText] = useState(data.commentContents);
  const [nickname, setNickname] = useState('');

  useEffect(() => {
    const getCommtentUserNickname = async () => {
      const result = await axios.get(`${PROXY}/member/profile?memberId=${data.writerId}`);
      setNickname(result.data.nickname);
    };
    getCommtentUserNickname();
  }, []);

  const navigate = useNavigate();
  const createdAt = new Date(String(data.writeDate)).toLocaleDateString().slice(0, 13);
  const processedDate = createdAt.slice(0, -1);
  const deleteCommentHandler = () => {
    deleteComment(data.commentId);
    const newCommentList = renewComments.filter((el) => el.commentId !== data.commentId);
    setRenewCommentsList(newCommentList);
  };

  const editCommentHandler = () => {
    setEditComment(true);
  };

  const commentHandler = (e: ChangeEvent<HTMLInputElement>) => {
    setText(e.target.value);
  };

  const updateCommentHandler = () => {
    updateComment(
      {
        commentContents: text,
        commentId: data.commentId,
        writerId: data.writerId,
      },
      data.commentId,
    );
    const newCommentList = renewComments.map((el) =>
      el.commentId === data.commentId
        ? {
            commentContents: text,
            commentId: data.commentId,
            writerId: data.writerId,
            writeDate: String(new Date()),
          }
        : el,
    );
    setRenewCommentsList(newCommentList);

    setEditComment(false);
  };

  const imgUrl = `${PROXY}/member/profileImage/get?memberId=${data.writerId}`;
  return (
    <>
      <CommentItemWrapper>
        <Image alt="practice" src={`${PROXY}/user2.png`} />
        <div>FE준비생</div>
        <CommentItemHeader>
          <span>{data.writeDate}</span>
        </CommentItemHeader>
        <CommentItemContent>{data.commentContents}</CommentItemContent>
        {user.memberId === data.writerId ? (
          <CommentItemFooter>
            {editComment ? (
              <>
                <TextInput type="text" value={text} onChange={commentHandler} placeholder={''} />
                <TextButton
                  text="text"
                  isGray={true}
                  btnText={'취소'}
                  onClick={() => setEditComment(false)}
                />
                <TextButton text="확인" isGray={true} btnText={'확인'} onClick={updateCommentHandler} />
              </>
            ) : (
              <>
                <TextButton text="수정" isGray={true} btnText={'수정'} onClick={editCommentHandler} />
                <TextButton text="삭제" isGray={true} btnText={'삭제'} onClick={deleteCommentHandler} />
              </>
            )}
          </CommentItemFooter>
        ) : null}
      </CommentItemWrapper>
    </>
  );
};

const CommentItemWrapper = styled.div`
  border: 1px solid #e9ecef;
  border-radius: 4px;
  padding: 1rem;
  margin-top: 1rem;
  width: 1000px;
  @media screen and (max-width: 500px) {
    width: 100%;
  }
`;

const CommentItemHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.5rem;
  span {
    color: #868e96;
    font-size: 0.875rem;
  }
`;

const CommentItemContent = styled.div`
  font-size: 1rem;
`;

const CommentItemFooter = styled.div`
  display: flex;
  justify-content: flex-end;
  padding: 5px;
  margin-top: 0.5rem;
  button {
    margin-right: 0.5rem;
    white-space: nowrap;
  }
  input {
    width: 685px;
    margin-right: 15px;
    border-radius: 0px;
  }
  @media screen and (max-width: 500px) {
    input {
      width: 270px;
    }
  }
`;

export const Image = styled.img`
  width: 2rem;
  height: 2rem;
  border-radius: 50%;
  margin-right: 0.5rem;
  padding-right: 0;
`;

export default CommentItem;
