import React from "react";
import styled from "styled-components";
import CupertinoHeaderWithAddButton from "./CupertinoHeaderWithAddButton";
import {Link} from "react-router-dom";

function Test(props) {
    return (
        <Container {...props}>
            <Header>
                <HeaderXStack>
                    <CupertinoHeaderWithAddButton
                        style={{
                            height: 82,
                            width: 1366,
                            position: "absolute",
                            left: 0,
                            top: 0
                        }}
                    ></CupertinoHeaderWithAddButton>
                    <Logo src={require("../assets/images/logo_fertig_transp.png")}></Logo>
                    <Link to="/Startseite">
                        <LogoButton>
                            <ButtonOverlay></ButtonOverlay>
                        </LogoButton>
                    </Link>
                </HeaderXStack>
            </Header>
        </Container>
    );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const ButtonOverlay = styled.button`
 display: block;
 background: none;
 height: 100%;
 width: 100%;
 border:none
 `;
const Header = styled.div`
  width: 1366px;
  height: 82px;
  flex-direction: column;
  display: flex;
`;

const Logo = styled.img`
  top: 11px;
  left: 33px;
  width: 84px;
  height: 60px;
  position: absolute;
  object-fit: contain;
`;

const LogoButton = styled.div`
  top: 19px;
  width: 65px;
  height: 63px;
  position: absolute;
  left: 43px;
  overflow: visible;
  background-color: rgba(230, 230, 230,1);
  opacity: 0;
  border: none;
`;

const HeaderXStack = styled.div`
  width: 1366px;
  height: 82px;
  position: relative;
`;

export default Test;
