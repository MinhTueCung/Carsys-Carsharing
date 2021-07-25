import React from "react";
import styled from "styled-components";


function Startseite() {

    return(
        <div style={{position: "relative"}}>
            <img src={require("../assets/images/carsharing_slide2.png")} style={{position: "absolute"}} alt={"carsharing_slide1.png"} />
            <Standarttextr>
                <h1 className="display-3" style={{fontFamily: "Century Gothic"}}>CAR GATEWAY</h1>
                <CustomP className="lead" style={{marginLeft: "absolute"}}>Ihre Carsharing Lösung</CustomP>
                <hr className="my-2" />
                <CustomP>"sharing is caring"</CustomP>
                <CustomP>Jetzt Registrieren, Reservieren & Ausprobieren!</CustomP>
            </Standarttextr>
        </div>
    )
}

const Standarttextr = styled.div`
  position: absolute;
  background-color: rgba(255,255,255,0.75);
  z-index: 100
  
  color: #121212;
  font-size: 28px;
  margin-top: 150px;
  margin-left: 58px;
`;

const CustomP = styled.p`
    margin: 20px
    font-family: Century Gothic
`;

export default Startseite;
