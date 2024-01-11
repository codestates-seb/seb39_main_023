import { useState } from 'react';
import { Container, Top, Btns, BtnWrapper, Bottom } from './style';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faEdit } from '@fortawesome/free-solid-svg-icons';
import MyInfo from '../../components/User/MyInfo';
import MyAccount from '../../components/User/MyAccount';
const Mypage = () => {
  const [isInfo, setIsInfo] = useState(true);
  return (
    <Container>
      <Top>
        <div className="title">내 계정</div>
        <Btns>
          <BtnWrapper onClick={() => setIsInfo(true)} className={isInfo ? 'active' : 'deActive'}>
            {/* <FontAwesomeIcon icon={faUser} className="icon" /> */}
            <button>회원정보</button>
          </BtnWrapper>
          <BtnWrapper onClick={() => setIsInfo(false)} className={isInfo ? 'deActive' : 'active'}>
            {/* <FontAwesomeIcon icon={faEdit} className="icon" /> */}
            <button>계정</button>
          </BtnWrapper>
        </Btns>
      </Top>
      <Bottom>{isInfo ? <MyInfo /> : <MyAccount />}</Bottom>
    </Container>
  );
};
export default Mypage;
