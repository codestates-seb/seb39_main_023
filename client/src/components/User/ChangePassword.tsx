import { Container, ChangePasswordBox } from './changePassword-style';
import DefaultInput from '../../UI/input/DefaultInput';
import Button from '../../UI/button/Button';
import { ChangeEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { UserContext } from '../../context/context';
import { BtnWrapper } from './nickname-style';
import { PW_REGEXP, MSG_06, MSG_07, MSG_08, MSG_09 } from '../../constants';
import { changePassword } from '../../Utils';
interface IPassword {
  newPassword: string;
  reNewPassword: string;
}
interface Message {
  newPassword: string;
  reNewPassword: string;
}
const ChangePassword = () => {
  const navigate = useNavigate();
  const { setUser } = useContext(UserContext);
  const token = localStorage.getItem('token');
  const [password, setPassword] = useState<IPassword>({
    newPassword: '',
    reNewPassword: '',
  });
  const [message, setMessage] = useState<Message>({
    newPassword: '>',
    reNewPassword: '>',
  });
  const isValidPW = (str: string) => {
    return PW_REGEXP.test(str);
  };
  const handlePasswordInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    setPassword({
      ...password,
      [name]: value,
    });
    switch (name) {
      case 'newPassword':
        isValidPW(value)
          ? setMessage({ ...message, ['newPassword']: MSG_07 })
          : setMessage({ ...message, ['newPassword']: MSG_06 });
        break;
      case 'reNewPassword':
        isValidPW(value) && value === password.newPassword
          ? setMessage({ ...message, ['reNewPassword']: MSG_08 })
          : setMessage({ ...message, ['reNewPassword']: MSG_09 });
        break;
      default:
        setMessage({
          ...{
            newPassword: '>',
            reNewPassword: '>',
          },
        });
    }
  };
  const checkIfValidPassword = async () => {
    if (
      !isValidPW(password.newPassword) ||
      !isValidPW(password.reNewPassword) ||
      !password.newPassword ||
      !password.reNewPassword ||
      password.newPassword !== password.reNewPassword
    ) {
      return;
    } else {
      return true;
    }
  };
  const sendNewPasswordToServer = async () => {
    const validPassword = await checkIfValidPassword();
    if (token && validPassword) {
      try {
        const result = await changePassword(password.newPassword);
        if (result === 200) {
          setMessage({
            ...message,
            ['reNewPassword']: '??????????????? ??????????????? ?????????????????????. ????????? ???????????? ??????????????? :)',
          });
        }
        return result;
      } catch {
        alert('???????????? ????????? ??????????????????. ?????? ??? ?????? ?????????????????? ???_???');
      }
    }
  };
  const giveTimeToReadDescription = () => {
    setTimeout(() => {
      forceUserToLogout();
    }, 2000);
  };
  const forceUserToLogout = () => {
    setUser({
      memberId: 0,
      loginId: '',
      email: '',
      name: '',
      nickname: '',
      createdAt: '',
      profileImageId: 0,
    });
    localStorage.removeItem('userInfo');
    navigate('/login');
  };
  const tryChangPassword = async () => {
    const result = await sendNewPasswordToServer();
    if (result === 200) {
      giveTimeToReadDescription();
    } else {
      setMessage({
        ...message,
        ['reNewPassword']: '???????????? ????????? ??????????????????. ?????? ?????????????????? ???_???',
      });
    }
  };
  const handleCancleButton = () => {
    setPassword({ ...password, newPassword: '', reNewPassword: '' });
    setMessage({ ...message, newPassword: '>', reNewPassword: '>' });
  };
  return (
    <Container>
      <ChangePasswordBox>
        <div className="title">???????????? ??????</div>
        <form>
          <DefaultInput
            label="??? ????????????"
            type="password"
            name="newPassword"
            value={password.newPassword}
            onChange={handlePasswordInputChange}
            message={message.newPassword}
            placeholder={MSG_06}
          />
          <DefaultInput
            label="??? ???????????? ??????"
            type="password"
            name="reNewPassword"
            value={password.reNewPassword}
            message={message.reNewPassword}
            onChange={handlePasswordInputChange}
            placeholder="??????????????? ?????? ??????????????????"
          />
        </form>
      </ChangePasswordBox>
      <BtnWrapper>
        <Button text={'??????'} type={'white'} width={'short'} onClick={handleCancleButton} />
        <Button text={'??????'} type={'white'} width={'short'} onClick={tryChangPassword} />
      </BtnWrapper>
    </Container>
  );
};
export default ChangePassword;
