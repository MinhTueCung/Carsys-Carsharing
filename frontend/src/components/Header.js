import React from "react";
import styled from "styled-components";

function Header(props) {
    return <Container {...props}></Container>;
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  background-color: #EFEFF4;
  padding-right: 8px;
  padding-left: 8px;
`;

export default Header;
