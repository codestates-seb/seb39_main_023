import { useContext, useState } from 'react';
import { UserContext } from '../../context/context';
import { Container, Info, CheckBox, BtnWrapper } from './deleteaccount-style';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
const DeleteIdAccount = () => {
  const navigate = useNavigate();
  const { setUser } = useContext(UserContext);
  const token = localStorage.getItem('token');
  const [isChecked, setIsChecked] = useState(false);
  const deleteAccount = async () => {
    if (token) {
      try {
        const res = await axios.delete(`http://3.35.90.143:54130/member/delete`, {
          headers: { Authorization: token },
        });
        if (res.status === 200) {
          localStorage.removeItem('userInfo');
          localStorage.removeItem('token');
          setUser({
            memberId: 0,
            loginId: '',
            email: '',
            name: '',
            nickname: '',
            createdAt: '',
            profileImageId: 0,
          });
          alert('회원탈퇴가 정상적으로 완료되었습니다 :)');
          navigate('/', { replace: true });
        }
      } catch {
        alert('에러가 발생했습니다. 잠시 후 다시 시도해주세요 ㅜ_ㅜ');
      }
    }
  };
  return (
    <Container>
      <div className="title">계정삭제</div>
      <Info>
        <p className="descript">계정을 삭제하면 게시글, 관심 등 모든 활동 정보가 삭제됩니다.</p>
      </Info>
      <CheckBox>
        <input
          type="checkbox"
          onClick={() => {
            setIsChecked(!isChecked);
          }}
        ></input>
        <p className="descript">계정 삭제에 관한 정책을 읽고 이에 동의합니다.</p>
      </CheckBox>
      <BtnWrapper>
        <button
          className={isChecked ? 'active' : ''}
          disabled={isChecked ? false : true}
          onClick={deleteAccount}
        >
          회원탈퇴
        </button>
      </BtnWrapper>
    </Container>
  );
};
export default DeleteIdAccount;