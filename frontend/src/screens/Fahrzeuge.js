import React from "react";
import styled from "styled-components";

function Fahrzeuge(props) {
    return (
        <Stack>
            <HeaderXStack>
                <Image2 src={require("../assets/images/toyota_aygo.jpg")}></Image2>
                <Image3 src={require("../assets/images/ford_fiesta.png")}></Image3>
                <Image4 src={require("../assets/images/ford_focus.png")}></Image4>
                <Image5 src={require("../assets/images/ford_transport.webp")}></Image5>
            </HeaderXStack>
            <Xs>XS</Xs>
            <S>S</S>
            <M>M</M>
            <L2>L</L2>
        </Stack>
    );
}

const Stack = styled.div`
  width: 1920px;
  height: 768px;
  position: relative;
  display: flex;
`;

const Image2 = styled.img`
  top: 135px;
  left: 137px;
  width: 200px;
  height: 200px;
  position: absolute;
  object-fit: contain;
`;

const Image3 = styled.img`
  top: 297px;
  left: 164px;
  width: 147px;
  height: 173px;
  position: absolute;
  object-fit: contain;
`;

const Image4 = styled.img`
  top: 440px;
  left: 159px;
  width: 157px;
  height: 178px;
  position: absolute;
  object-fit: contain;
`;

const Image5 = styled.img`
  top: 568px;
  left: 137px;
  width: 200px;
  height: 200px;
  position: absolute;
  object-fit: contain;
`;

const HeaderXStack = styled.div`
  top: 0px;
  left: 0px;
  width: 1920px;
  height: 768px;
  position: absolute;
`;

const Xs = styled.span`
  font-family: Roboto;
  top: 207px;
  left: 27px;
  position: absolute;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  height: 57px;
  width: 90px;
  font-size: 30px;
`;

const S = styled.span`
  font-family: Roboto;
  top: 356px;
  left: 27px;
  position: absolute;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  height: 57px;
  width: 90px;
  font-size: 30px;
`;

const M = styled.span`
  font-family: Roboto;
  top: 501px;
  left: 27px;
  position: absolute;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  height: 57px;
  width: 90px;
  font-size: 30px;
`;

const L2 = styled.span`
  font-family: Roboto;
  top: 648px;
  left: 27px;
  position: absolute;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  height: 57px;
  width: 90px;
  font-size: 30px;
`;

export default Fahrzeuge;
