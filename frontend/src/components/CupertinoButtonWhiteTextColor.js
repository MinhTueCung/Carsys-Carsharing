import React from "react";
import styled from "styled-components";

function CupertinoButtonWhiteTextColor(props) {
    return (
        <Container {...props}>
            <Caption>Button</Caption>
        </Container>
    );
}

const Container = styled.div`
  display: flex;
  background-color: transparent;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  border-radius: 5px;
`;

const Caption = styled.span`
  font-family: Roboto;
  color: #fff;
  font-size: 17px;
  font-weight: 500;
`;

export default CupertinoButtonWhiteTextColor;
