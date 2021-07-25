import React from "react";
import styled from "styled-components";

function Button(props) {
    return (
        <Container {...props}>
            <EllipseFiller></EllipseFiller>
            <svg
                viewBox="0 0 62.51 59.86"
                style={{
                    width: 63,
                    height: 60,
                    alignSelf: "flex-end"
                }}
            >
                <ellipse
                    stroke="rgba(230, 230, 230,1)"
                    strokeWidth={0}
                    fill="rgba(230, 230, 230,1)"
                    cx={31}
                    cy={30}
                    rx={31}
                    ry={30}
                ></ellipse>
            </svg>
        </Container>
    );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
`;

const EllipseFiller = styled.div`
  flex: 1 1 0%;
  flex-direction: row;
  display: flex;
`;

export default Button;
