import styled, { keyframes } from 'styled-components';
export const ModalBackDrop = styled.div`
  display: flex;
  position: fixed;
  z-index: 999;
  left: 0;
  bottom: 0;
  right: 0;
  top: 0;
  @media screen and (max-width: 500px) {
    top: 10px;
    justify-content: center;
  }
`;
export const Container = styled.div<{ modalOpen: boolean }>`
  display: flex;
  z-index: 99;
  flex-direction: column;
  position: relative;
  top: 52px;
  left: 150px;
  padding: 7px;
  height: 40px;
  opacity: 1;
  /* background-color: white; */

  @media screen and (max-width: 500px) {
    align-items: center;
    width: 340px;
    height: 440px;
    top: 5px;
    left: 0.01px;
    opacity: 1;
    background-color: white;
    box-shadow: rgba(100, 100, 100, 0.1) 1px -1px 3px 2px;
    animation: ${(props) => (props.modalOpen ? ToBottom : ToTop)} 0.4s;
  }
`;
export const Top = styled.div`
  display: none;
  @media screen and (max-width: 500px) {
    display: flex;
    align-items: center;
    padding: 5px 0px;
    padding-left: 15px;
    width: 100%;
    color: #474747;
    .title {
      font-weight: 500;
      font-size: 16px;
      width: 94%;
      text-align: center;
    }
    .icon {
      font-size: 17px;
      cursor: pointer;
    }
  }
`;
export const Bottom = styled.div<{ modalOpen: boolean }>`
  display: flex;
  animation: ${(props) => (props.modalOpen ? ToBottom : ToTop)} 0.4s;

  @media screen and (max-width: 500px) {
    margin-top: 5px;
    display: flex;
    flex-direction: column;
    min-width: 280px;
    align-items: flex-start;
    animation: none;
  }
`;
const ToBottom = keyframes`
  from {
      transform: translateY(-100%);
  }
  to {
      transform: translate(0%);
  }
`;
const ToTop = keyframes`
  from {
    transform: translate(0%);
  }
  to {
    transform: translateY(-100%);
  }
`;

export const Item = styled.div`
  cursor: pointer;
  color: black;
  font-size: 14px;
  font-weight: 500;
  margin-right: 10px;
  white-space: nowrap;
  color: #464646;
  &:hover {
    color: #96d1cc;
    transition: 0.2s;
  }
  @media screen and (max-width: 500px) {
    cursor: pointer;
    color: #464646;
    font-size: 14px;
    font-weight: 300;
    padding-top: 10px;
  }
`;
