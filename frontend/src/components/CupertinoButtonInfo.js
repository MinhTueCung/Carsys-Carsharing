import React from "react";
import styled from "styled-components";

function CupertinoButtonInfo(props) {
    return (
        <Container {...props}>
            <Button>Button</Button>
        </Container>
    );
}

const Container = styled.div`
  display: flex;
  background-color: #007AFF;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  border-radius: 5px;
  padding-left: 16px;
  padding-right: 16px;
`;

const Button = styled.span`
  font-family: Roboto;
  color: #fff;
  font-size: 17px;
  font-weight: 500;
`;

export default CupertinoButtonInfo;
