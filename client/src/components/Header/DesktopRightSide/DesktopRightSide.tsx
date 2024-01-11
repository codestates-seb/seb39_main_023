import styled from 'styled-components';
import TextButton from '../../../UI/button/TextButton';
import Button from '../../../UI/button/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
interface PropsType {
  setUserModal: (state: boolean) => void;
  memberId: any;
}
const DesktopRightSide = ({ setUserModal, memberId }: PropsType) => {
  const navigate = useNavigate();
  return (
    <DesktopTopRight>
      {memberId ? (
        <FontAwesomeIcon
          icon={faUser}
          onClick={memberId ? () => setUserModal(true) : () => navigate('/login')}
          className="icon"
        />
      ) : (
        <>
          <TextButton isGray={true} btnText={'로그인'} onClick={() => navigate('/login')} />
          <TextButton isGray={true} btnText={'회원가입'} onClick={() => navigate('/signup')} />
        </>
      )}
      <Button
        text={'글쓰기 +'}
        width={'short'}
        onClick={() => navigate('/postwrite')}
        // onClick={memberId ? () => navigate('/postwrite') : () => navigate('/login')}
      />
    </DesktopTopRight>
  );
};
export default DesktopRightSide;
export const DesktopTopRight = styled.div`
  display: flex;
  width: 200px;
  align-items: center;
  button {
    font-size: 13px;
    margin-left: 15px;
    white-space: nowrap;
  }
  .icon {
    cursor: pointer;
    color: #95d1cc;
    width: 25px;
    height: 20px;
  }
  @media screen and (max-width: 500px) {
    display: none;
  }
`;
