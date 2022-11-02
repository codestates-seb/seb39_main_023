import styled from 'styled-components';
import { ReactComponent as Git } from '../../asessts/img/github.svg';
const MyFooter = styled.footer`
  padding-top: 5px;
  font-size: 12px;
  color: black;
  background-color: white;
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: absolute;
  bottom: 0;
  border-top: 1px solid #f1efef;
  box-shadow: rgba(100, 100, 100, 0.1) 0px -2px 6px;
  @media screen and (max-width: 500px) {
    flex-direction: column;
    width: 80%;
    div {
      display: none;
    }
  }
`;
const Wrapper = styled.footer`
  display: flex;
  align-items: center;
  span {
    margin-left: 2px;
  }
  a {
    margin-right: 3px;
    color: black;
    text-decoration: none;
  }
  a:hover {
    color: #686767;
  }
  @media screen and (max-width: 500px) {
    span {
      display: none;
    }
  }
`;
const GitIconSVG = styled(Git)`
  width: 15px;
  margin-right: 3px;
`;

const Footer = () => {
  const gitUrl = `https://github.com/codestates-seb/seb39_main_023/tree/main`;
  return (
    <MyFooter>
      <div>@ 2022 빌리지뭐, All rights reserved.</div>
      <Wrapper>
        <GitIconSVG />
        <a href={gitUrl}>Team 023 Github repository</a>
      </Wrapper>
      <Wrapper>
        <span>BE 장원용</span>
        <span>FE 남충현</span>
        <span>FE 문도연</span>
      </Wrapper>
    </MyFooter>
  );
};
export default Footer;
