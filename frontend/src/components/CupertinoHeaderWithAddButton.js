import React from "react";
import styled from "styled-components";

function CupertinoHeaderWithAddButton(props) {
    return (
        <Container {...props}>
            <LeftWrapper></LeftWrapper>
        </Container>
    );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  background-color: #EFEFF4;
  padding-right: 8px;
  padding-left: 8px;
`;

const LeftWrapper = styled.div`
  flex: 1 1 0%;
  align-items: flex-start;
  justify-content: center;
  display: flex;
  flex-direction: column;
`;

export default CupertinoHeaderWithAddButton;
