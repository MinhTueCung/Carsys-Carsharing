import React from "react";
import styled from "styled-components";

function PasswortVergessen(props) {
    return (
        <>
            <Group1>
                <EMailRow>
                    <EMail>E-Mail:</EMail>
                    <BenutzernameInput1 placeholder=""></BenutzernameInput1>
                </EMailRow>
            </Group1>
            <AnmeldenButton1>
                <ButtonOverlay>
                    <Rect1>
                        <Senden>Senden</Senden>
                    </Rect1>
                </ButtonOverlay>
            </AnmeldenButton1>
        </>
    );
}

const ButtonOverlay = styled.button`
 display: block;
 background: none;
 height: 100%;
 width: 100%;
 border:none
 `;

const Group1 = styled.div`
  width: 553px;
  height: 43px;
  flex-direction: row;
  display: flex;
  margin-top: 67px;
  margin-left: 40px;
`;

const EMail = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  height: 43px;
  width: 211px;
  font-size: 25px;
`;

const BenutzernameInput1 = styled.input`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  height: 43px;
  width: 410px;
  font-size: 25px;
  border-width: 1px;
  border-color: #000000;
  margin-left: 55px;
  border-style: solid;
  background: transparent;
`;

const EMailRow = styled.div`
  height: 43px;
  flex-direction: row;
  display: flex;
  flex: 1 1 0%;
  margin-right: -123px;
`;

const AnmeldenButton1 = styled.div`
  width: 217px;
  height: 59px;
  flex-direction: column;
  display: flex;
  margin-top: 160px;
  margin-left: 40px;
  border: none;
`;

const Rect1 = styled.div`
  width: 217px;
  height: 59px;
  background-color: rgba(74,144,226,1);
  border-radius: 30px;
  flex-direction: column;
  display: flex;
`;

const Senden = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 700;
  color: #121212;
  font-size: 25px;
  width: 123px;
  height: 30px;
  margin-top: 14px;
  margin-left: 62px;
`;

export default PasswortVergessen;
