import { ChangeEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import DefaultInput from '../../UI/input/DefaultInput';
import {
  trySignUp,
  checkDuplicatedLoginId,
  checkDuplicatedNickname,
  checkDuplicatedEmail,
} from '../../Utils';
import Button from '../../UI/button/Button';
import {
  ID_REGEXP,
  PW_REGEXP,
  EMAIL_REGEXP,
  USERNAME_REGEXP,
  NICKNAME_REGEXP,
  MSG_01,
  MSG_02,
  MSG_03,
  MSG_04,
  MSG_05,
  MSG_06,
  MSG_07,
  MSG_08,
  MSG_09,
  MSG_10,
  MSG_11,
  MSG_12,
  MSG_13,
  MSG_14,
  MSG_15,
  MSG_16,
  MSG_17,
  MSG_18,
} from '../../constants';
import { Signup } from '../../types/user';
import { Container, BtnWrapper } from './style';

interface Message {
  loginId: string;
  password: string;
  rePassword: string;
  name: string;
  nickname: string;
  email: string;
}
const SignupInput = () => {
  const navigate = useNavigate();
  const [userInfo, setUserInfo] = useState<Signup>({
    loginId: '',
    password: '',
    rePassword: '',
    name: '',
    nickname: '',
    email: '',
  });
  const [message, setMessage] = useState<Message>({
    loginId: '>',
    password: '>',
    rePassword: '>',
    name: '>',
    nickname: '>',
    email: '>',
  });
  const handleUserInfoChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value,
    });
    switch (name) {
      case 'loginId':
        isValidId(value)
          ? setMessage({ ...message, ['loginId']: MSG_02 })
          : setMessage({ ...message, ['loginId']: MSG_01 });
        break;
      case 'password':
        isValidPW(value)
          ? setMessage({ ...message, ['password']: MSG_07 })
          : setMessage({ ...message, ['password']: MSG_06 });
        break;
      case 'rePassword':
        isValidPW(value) && userInfo.password === value
          ? setMessage({ ...message, ['rePassword']: MSG_08 })
          : setMessage({ ...message, ['rePassword']: MSG_09 });
        break;
      case 'name':
        isValidUsername(value)
          ? setMessage({ ...message, ['name']: '>' })
          : setMessage({ ...message, ['name']: MSG_10 });
        break;
      case 'nickname':
        isValidNickname(value)
          ? setMessage({ ...message, ['nickname']: MSG_02 })
          : setMessage({ ...message, ['nickname']: MSG_11 });
        break;
      case 'email':
        isValidEmail(value)
          ? setMessage({ ...message, ['email']: '>' })
          : setMessage({ ...message, ['email']: MSG_15 });
        break;
      default:
        setMessage({
          ...{
            loginId: '>',
            password: '>',
            rePassword: '>',
            name: '>',
            nickname: '>',
            email: '>',
          },
        });
    }
  };
  // ???????????????
  const isValidId = (str: string) => {
    return ID_REGEXP.test(str);
  };
  const isValidPW = (str: string) => {
    return PW_REGEXP.test(str);
  };
  const isValidUsername = (str: string) => {
    return USERNAME_REGEXP.test(str);
  };
  const isValidNickname = (str: string) => {
    return NICKNAME_REGEXP.test(str);
  };
  const isValidEmail = (str: string) => {
    return EMAIL_REGEXP.test(str);
  };
  // ????????????
  const checkUserID = async () => {
    if (isValidId(userInfo.loginId)) {
      try {
        const result = await checkDuplicatedLoginId(userInfo.loginId);
        {
          result === 'not exist'
            ? setMessage({ ...message, ['loginId']: MSG_03 })
            : setMessage({ ...message, ['loginId']: MSG_04 });
        }
      } catch {
        setMessage({ ...message, ['loginId']: MSG_05 });
      }
    }
  };
  const checkNickname = async () => {
    if (isValidNickname(userInfo.nickname)) {
      try {
        const result = await checkDuplicatedNickname(userInfo.nickname);
        {
          result === 'not exist'
            ? setMessage({ ...message, ['nickname']: MSG_13 })
            : setMessage({ ...message, ['nickname']: MSG_12 });
        }
      } catch {
        setMessage({ ...message, ['nickname']: MSG_14 });
      }
    }
  };
  const checkEmail = async () => {
    if (isValidEmail(userInfo.email)) {
      try {
        const result = await checkDuplicatedEmail(userInfo.email);
        {
          result === 'not exist'
            ? setMessage({ ...message, ['email']: MSG_17 })
            : setMessage({ ...message, ['email']: MSG_16 });
        }
      } catch {
        setMessage({ ...message, ['email']: MSG_18 });
      }
    }
  };
  // ???????????? ??????
  const handleRegisterUser = async () => {
    if (
      isValidId(userInfo.loginId) &&
      isValidPW(userInfo.password) &&
      isValidUsername(userInfo.name) &&
      isValidNickname(userInfo.nickname) &&
      isValidEmail(userInfo.email)
    ) {
      try {
        const result = await trySignUp(userInfo);
        {
          result === 201 ? navigate('/login', { replace: true }) : null;
        }
      } catch {
        alert('???????????????. ?????? ??? ?????? ??????????????????');
      }
    }
  };
  return (
    <>
      <Container>
        <Button text={'????????????'} type={'beige'} width={'short'} onClick={checkUserID} />

        <form>
          <DefaultInput
            label="?????????"
            name="loginId"
            value={userInfo.loginId}
            onChange={handleUserInfoChange}
            message={message.loginId}
            placeholder="???????????? ??????????????????"
          />
          <DefaultInput
            label="????????????"
            type="password"
            name="password"
            value={userInfo.password}
            onChange={handleUserInfoChange}
            message={message.password}
            placeholder="?????? ?????????, ??????, ???????????? ?????? 8??? ????????? ????????????"
          />
          <DefaultInput
            label="???????????? ??????"
            type="password"
            name="rePassword"
            value={userInfo.rePassword}
            onChange={handleUserInfoChange}
            message={message.rePassword}
            placeholder="?????? ?????????, ??????, ???????????? ?????? 8??? ????????? ????????????"
          />
          <DefaultInput
            label="??????"
            name="name"
            value={userInfo.name}
            onChange={handleUserInfoChange}
            message={message.name}
            placeholder="????????? ??????????????????"
          />
          <DefaultInput
            label="?????????"
            name="nickname"
            value={userInfo.nickname}
            onChange={handleUserInfoChange}
            message={message.nickname}
            placeholder="2-15??? ????????? ??????????????????"
          />
          <DefaultInput
            label="?????????"
            name="email"
            onChange={handleUserInfoChange}
            message={message.email}
            placeholder="???????????? ??????????????????"
          />
        </form>
        <Button text={'????????????'} type={'beige'} width={'short'} onClick={checkNickname} />
        <Button text={'????????????'} type={'beige'} width={'short'} onClick={checkEmail} />
      </Container>
      <BtnWrapper>
        <Button text={'????????????'} type={'beige'} onClick={handleRegisterUser} />
      </BtnWrapper>
    </>
  );
};
export default SignupInput;
