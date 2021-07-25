import React from "react";
import styled from "styled-components";
import MaterialCommunityIconsIcon from "react-native-vector-icons/dist/MaterialCommunityIcons";

function MaterialDisabledTextbox(props) {
    return (
        <Container {...props}>
            <InputStyle placeholder="Disabled Textbox" editable={false}></InputStyle>
            <MaterialCommunityIconsIcon
                name="information-outline"
                style={{
                    color: "#384850",
                    fontFamily: "Roboto",
                    fontSize: 24,
                    paddingRight: 8,
                    opacity: 0.7
                }}
            ></MaterialCommunityIconsIcon>
        </Container>
    );
}

const Container = styled.div`
  display: flex;
  border-bottom-width: 1px;
  border-color: #D9D5DC;
  background-color: transparent;
  flex-direction: row;
  align-items: center;
`;

const InputStyle = styled.input`
  font-family: Roboto;
  color: #000;
  padding-right: 5px;
  font-size: 16px;
  align-self: stretch;
  flex: 1 1 0%;
  line-height: 16px;
  padding-top: 16px;
  padding-bottom: 8px;
  border: none;
  background: transparent;
  display: flex;
  flex-direction: column;
`;

export default MaterialDisabledTextbox;
