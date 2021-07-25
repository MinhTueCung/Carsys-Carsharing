import React, {Component} from "react";
import {Alert, Button, Container, Input, InputGroup, InputGroupAddon, InputGroupText} from "reactstrap";

class Registrieren extends Component {

    state =
        {
            login: [],
            address: []
        }

    message =
        {}

    alertState =
        {
            success: false,
            error: false,
            warning: false,
            warning_name: false,
            warning_mail: false,
            warning_young: false,
            warning_future: false,
            warning_empty: false
        }


    sendData()
    {
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(this.state)
        };
        fetch("/v1/member", requestOptions)
            .then((response) => {
                if (response.status === 200) { //korrekt
                    console.warn("SUCCESSS")
                    console.warn(response.status)
                    this.alertState = ({
                        success: true,
                        warning: false,
                        error: false,
                        warning_mail: false,
                        warning_name: false,
                        warning_young: false,
                        warning_future: false,
                        warning_empty: false
                    })
                    this.setState({requestFailed: false})


                } else if (response.status === 500) { //Eingabe nicht korrekt, PLZ ist String keine Zahl etc.
                    console.warn("INHALT FALSCH")
                    this.alertState = ({
                        success: false,
                        warning: false,
                        error: true,
                        warning_mail: false,
                        warning_name: false,
                        warning_young: false,
                        warning_future: false,
                        warning_empty: false
                    })
                    this.setState({requestFailed: true})
                } else if (response.status === 403) { //Email schon vergeben
                    console.warn("MAIL VERGEBEN")
                    this.alertState = ({
                        success: false,
                        warning: false,
                        error: false,
                        warning_mail: true,
                        warning_name: false,
                        warning_young: false,
                        warning_future: false,
                        warning_empty: false
                    })
                    this.setState({requestFailed: true})
                } else if (response.status === 406) { //Name schon vergeben
                    console.warn("NAME VERGEBEN")
                    this.alertState = ({
                        success: false,
                        warning: false,
                        error: false,
                        warning_mail: false,
                        warning_name: true,
                        warning_young: false,
                        warning_future: false,
                        warning_empty: false
                    })
                    this.setState({requestFailed: true})
                } else if (response.status === 409) { //Zukunftsdatum
                    console.warn("ZUKUNFT")
                    this.alertState = ({
                        success: false,
                        warning: false,
                        error: false,
                        warning_mail: false,
                        warning_name: false,
                        warning_young: false,
                        warning_future: true,
                        warning_empty: false
                    })
                    this.setState({requestFailed: true})
                } else if (response.status === 405) { //Minderjährig
                    console.warn("ZU JUNG")
                    this.alertState = ({
                        success: false,
                        warning: false,
                        error: false,
                        warning_mail: false,
                        warning_name: false,
                        warning_young: true,
                        warning_future: false,
                        warning_empty: false
                    })
                    this.setState({requestFailed: true})
                } else if (response.status === 417) { //leere Felder
                    console.warn("LEER")
                    this.alertState = ({
                        success: false,
                        warning: false,
                        error: false,
                        warning_mail: false,
                        warning_name: false,
                        warning_young: false,
                        warning_future: false,
                        warning_empty: true
                    })
                    this.setState({requestFailed: true})
                } else {
                    console.warn("NICHT MOEGLICH") //Fehler
                    console.warn(response.status)
                    this.alertState = ({
                        success: false,
                        warning: true,
                        error: false,
                        warning_mail: false,
                        warning_name: false,
                        warning_young: false,
                        warning_future: false,
                        warning_empty: false
                    })
                    this.setState({requestFailed: true})

                }
            })
    }

    render() {
        return (
            <>
                <Container fluid><h2>Registrieren</h2></Container>
                <Container style={{marginTop: "1%", marginBottom: "1%"}}>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%"}}>
                            <InputGroupText>Vorname</InputGroupText>
                        </InputGroupAddon>
                        <Input name="firstName" onChange={event => this.handleChange(event)} />

                        <InputGroupAddon addonType="prepend" style={{marginLeft: "1%"}}>
                            <InputGroupText>Nachname</InputGroupText>
                        </InputGroupAddon>
                        <Input name="lastName" onChange={event => this.handleChange(event)}/>
                    </InputGroup>

                    <InputGroup>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"1%"}}>
                            <InputGroupText>Straße</InputGroupText>
                        </InputGroupAddon>
                        <Input name="street" onChange={event => this.fillAddress(event)} style={{marginTop:"1%"}} />

                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"1%"}}>
                            <InputGroupText>Hausnummer</InputGroupText>
                        </InputGroupAddon>
                        <Input name="houseno" onChange={event => this.fillAddress(event)} style={{marginTop:"1%"}}/>
                    </InputGroup>

                    <InputGroup>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"1%"}}>
                            <InputGroupText>PLZ</InputGroupText>
                        </InputGroupAddon>
                        <Input name="postcode" onChange={event => this.fillAddress(event)} style={{marginTop:"1%"}} />

                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"1%"}}>
                            <InputGroupText>Ort</InputGroupText>
                        </InputGroupAddon>
                        <Input name="city" onChange={event => this.fillAddress(event)} style={{marginTop:"1%"}}/>
                    </InputGroup>

                    <InputGroup>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"1%"}}>
                            <InputGroupText>Geburtsdatum</InputGroupText>
                        </InputGroupAddon>
                            <Input onChange={event => this.handleChange(event)} type="date" name="dateOfBirth" id="dateOfBirth"
                                   style={{marginTop:"1%"}}/>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"1%"}}>
                            <InputGroupText>E-Mail</InputGroupText>
                        </InputGroupAddon>
                        <Input name="email" onChange={event => this.fillLogin(event)} style={{marginTop:"1%"}}/>
                    </InputGroup>

                    <InputGroup>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"1%"}}>
                            <InputGroupText>Telefon</InputGroupText>
                        </InputGroupAddon>
                        <Input name="phone" onChange={event => this.handleChange(event)} style={{marginTop:"1%"}} />

                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"1%"}}>
                            <InputGroupText>Führerscheinnummer</InputGroupText>
                        </InputGroupAddon>
                        <Input name="drivingLicenseNumber" onChange={event => this.handleChange(event)} style={{marginTop:"1%"}}/>
                    </InputGroup>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"3%"}}>
                            <InputGroupText>IBAN</InputGroupText>
                        </InputGroupAddon>
                        <Input name="iban" onChange={event => this.handleChange(event)} style={{marginTop:"3%"}} />
                    </InputGroup>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"3%"}}>
                            <InputGroupText>Benutzername</InputGroupText>
                        </InputGroupAddon>
                        <Input name="username" onChange={event => this.fillLogin(event)} style={{marginTop:"3%"}}/>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "2%", marginTop:"3%"}}>
                            <InputGroupText>Passwort</InputGroupText>
                        </InputGroupAddon>
                        <Input name="password" type={"password"} onChange={event => this.fillLogin(event)} style={{marginTop:"3%"}} />
                    </InputGroup>
                    <br/><br/>

                    <Button color="primary" style={{width: "10%",marginLeft:"2%"}} onClick={(() => this.sendData())}>Senden</Button>

                    <br/><br/>
                    <Alert id="success_alert" name="success_alert" color="success" isOpen={this.alertState.success}
                           style={{marginLeft:"2%"}}>
                        Erfolgreich registriert!
                    </Alert>
                    <Alert id="error_alert" name="error_alert" color="danger" isOpen={this.alertState.error}
                           style={{marginLeft:"2%"}}>
                        Server-Fehler aufgetreten!
                    </Alert>
                    <Alert id="warning_name_alert" name="warning_name_alert" color="warning"
                           isOpen={this.alertState.warning_name}
                           style={{marginLeft:"2%"}}>
                        Benutzername wird bereits verwendet!
                    </Alert>
                    <Alert id="warning_mail_alert" name="warning_mail_alert" color="warning"
                           isOpen={this.alertState.warning_mail}
                           style={{marginLeft:"2%"}}>
                        E-Mail wird bereits verwendet!
                    </Alert>
                    <Alert id="warning_young_alert" name="warning_young_alert" color="warning"
                           isOpen={this.alertState.warning_young}
                           style={{marginLeft:"2%"}}>
                        Sie müssen mindestens 18 Jahre alt sein zum registrieren!
                    </Alert>
                    <Alert id="warning_future_alert" name="warning_future_alert" color="warning"
                           isOpen={this.alertState.warning_future}
                           style={{marginLeft:"2%"}}>
                        Ungültiges Geburtsdatum! (Zukunft)
                    </Alert>
                    <Alert id="warning_empty_alert" name="warning_empty_alert" color="warning"
                           isOpen={this.alertState.warning_empty}
                           style={{marginLeft:"2%"}}>
                        Pflichtfelder sind nicht ausgefüllt!
                    </Alert>
                    <Alert id="warning_alert" name="warning_alert" color="warning" isOpen={this.alertState.warning}
                           style={{marginLeft:"2%"}}>>
                        Fehler ist aufgetreten!
                    </Alert>
                </Container>
            </>
        );
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state};
        item[name] = value;
        this.setState(item)
    }


    fillLogin(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let login = {...this.state.login};
        login[name] = value;
        this.setState({login})
    }

    fillAddress(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let address = {...this.state.address};
        address[name] = value;
        this.setState({address})
    }

}


export default Registrieren;
