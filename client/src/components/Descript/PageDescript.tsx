import styled from 'styled-components';
import { ReactComponent as Logo } from '../../asessts/img/biglogo.svg';
import { ReactComponent as success } from '../../asessts/img/success.svg';
const DescriptWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
  h4 {
    font-size: 20px;
    margin-bottom: 15px;
  }
  p {
    font-size: 14px;
    text-align: center;
  }
  @media screen and (max-width: 500px) {
    h4 {
      font-size: 18px;
    }
    p {
      width: 100%;
      font-size: 13px;
    }
  }
`;
const Back = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f4fcfb;
  padding: 20px 2px;
  width: 330px;
  margin: 30px 0;
  @media screen and (max-width: 500px) {
    padding: 20px 4px;
    width: 290px;
  }
`;
const LogoSVG = styled(Logo)`
  margin-bottom: 30px;
`;
const IconSVG = styled(success)`
  margin: 0 10px;
  margin-bottom: 30px;
`;

interface Prop {
  title: string;
  descript?: string;
  isSuccess?: boolean;
}
const PageDescript = ({ title, descript, isSuccess }: Prop) => {
  return (
    <DescriptWrapper>
      {isSuccess === true ? <IconSVG /> : <LogoSVG />}
      <h4>{title}</h4>
      {isSuccess === true ? (
        <Back>
          <p>{descript}</p>
        </Back>
      ) : (
        <p>{descript}</p>
      )}
    </DescriptWrapper>
  );
};
export default PageDescript;
