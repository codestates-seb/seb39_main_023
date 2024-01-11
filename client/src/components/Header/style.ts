import styled from 'styled-components';
import { ReactComponent as Logo } from '../../asessts/img/logo.svg';
export const MyHeader = styled.header`
  width: 100%;
  display: flex;
  justify-content: space-around;
  position: sticky;
  top: 0;
  background-color: white;
  opacity: 0.95;
  /* backdrop-filter: blur(30px); */
  border-bottom: 0.5px solid #f5f5f5;
  box-shadow: rgba(100, 100, 100, 0.1) 0px -100px 5px;
  margin-top: 5px;
  margin-bottom: 10px;
  @media screen and (max-width: 500px) {
    width: 100%;
    flex-direction: column;
    display: flex;
    align-items: center;
  }
`;
export const Top = styled.div`
  display: flex;
  @media screen and (max-width: 500px) {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 350px;
    height: 40px;
  }
`;

export const TopLeft = styled.div`
  display: flex;
  align-items: center;
`;
export const LogoWrapper = styled.div`
  display: flex;
  align-items: center;
  cursor: pointer;
  white-space: nowrap;
  .title {
    font-size: 23px;
    font-weight: 500;
    padding-bottom: 4px;
    margin-right: 12px;
  }
  @media screen and (max-width: 500px) {
    .title {
      font-size: 18px;
      padding-bottom: 1px;
    }
  }
`;
export const CategoryWrapper = styled.div<{
  menuModal: boolean;
}>`
  cursor: pointer;
  white-space: nowrap;
  padding-top: 15px;
  padding-bottom: 5px;
  color: ${(props) => (props.menuModal === true ? '#95d1cc' : '#464646')};
  border-bottom: ${(props) => (props.menuModal === true ? '3px solid #95d1cc' : '3px solid transparent')};
  &:hover {
    cursor: pointer;
    color: #95d1cc;
  }
  @media screen and (max-width: 500px) {
    display: none;
  }
`;
export const LogoSVG = styled(Logo)`
  margin: 0 10px;
`;
export const Icons = styled.div`
  display: none;
  @media screen and (max-width: 500px) {
    display: inline-block;
    .icon {
      cursor: pointer;
      color: #95d1cc;
      width: 25px;
      height: 18px;
      margin-left: 7px;
    }
  }
`;
