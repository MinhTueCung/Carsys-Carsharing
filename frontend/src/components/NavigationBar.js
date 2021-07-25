import React, {useState} from "react";
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavLink} from "reactstrap";


const NavigationBar = () => {

    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => setIsOpen(!isOpen);

    return (
        <div>
            <Navbar color="light" light expand="md">
                <NavbarBrand href="/Startseite" style={{marginLeft:"1%"}}>
                    <img src={require("../assets/images/logo_fertig_transp.png")} width="91px" height="100%"
                         object-fit="contain" alt={<NavLink href="/Home">Home</NavLink>}/>
                </NavbarBrand>
                <NavbarToggler onClick={toggle}/>
                <Collapse isOpen={isOpen} navbar>
                    <Nav className="mr-auto" navbar style={{width:"100%"}}>
                        <NavLink href="/Standorte">Standort</NavLink>
                        <NavLink href="/Fahrzeuge">Fahrzeuge</NavLink>
                        <NavLink href="/Tarife">Tarife</NavLink>
                        <NavLink href="/Registrieren">Registrieren</NavLink>
                        <NavLink href="/Anmelden">Anmelden</NavLink>
                    </Nav>
                </Collapse>
            </Navbar>
        </div>
    );
}

export default NavigationBar;
