import styled from 'styled-components';
import { ReactComponent as Git } from '../../asessts/img/github.svg';
const MyFooter = styled.footer`
  font-size: 14px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-left: 20px;
  padding-right: 20px;
  height: 50px;
  margin-top: 100px;
  @media screen and (max-width: 500px) {
    flex-direction: column;
    align-items: start;

    padding-left: 15px;
    padding-right: 0px;
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
        <a href={gitUrl}>Team 023</a>
      </Wrapper>
    </MyFooter>
  );
};
export default Footer;
