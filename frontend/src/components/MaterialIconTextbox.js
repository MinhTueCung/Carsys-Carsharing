import React from "react";
import styled from "styled-components";
import MaterialCommunityIconsIcon from "react-native-vector-icons/dist/MaterialCommunityIcons";

function MaterialIconTextbox(props) {
    return (
        <Container {...props}>
            <MaterialCommunityIconsIcon
                name="calendar"
                style={{
                    color: "#616161",
                    fontSize: 24,
                    paddingLeft: 8
                }}
            ></MaterialCommunityIconsIcon>
            <InputStyle placeholder="Label"></InputStyle>
        </Container>
    );
}

const Container = styled.div`
  display: flex;
  background-color: transparent;
  flex-direction: row;
  align-items: center;
`;

const InputStyle = styled.input`
  font-family: Roboto;
  color: #000;
  margin-left: 16px;
  padding-right: 5px;
  font-size: 16px;
  align-self: stretch;
  flex: 1 1 0%;
  line-height: 16px;
  border-bottom-width: 1px;
  border-color: #D9D5DC;
  padding-top: 14px;
  padding-bottom: 8px;
  border: none;
  background: transparent;
  display: flex;
  flex-direction: column;
`;

export default MaterialIconTextbox;
