import React, {Component} from "react";
import {
    Fade,
    Button,
    Container,
    Form,
    Input,
    InputGroup,
    InputGroupAddon,
    InputGroupText,
    Label,
    FormFeedback
} from "reactstrap";

class Konto extends Component {


    initMember = {
        firstName: "",
        lastName: "",
        phone: "",
        login: [],
        dateOfBirth: "",
        iban: "",
        drivingLicenseNumber: "",
        tax: "",
        address: [],
        rfid: "",
        chipNumber: "",
        bills: "",
        tariff: [],
        active: true,
        userStatus: ""
    };

    constructor() {
        super();

        this.state = {
            member: this.initMember,
            fadePwIn:false,
            matchingPasswords: false
        }

    }

    componentDidMount() {
        fetch("/v1/member")
            .then(response => response.json())
            .then(jsonData => {
                this.setState({member: jsonData[0]});

            })
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let member = this.state.member;
        member[name] = value;
        this.setState(this.state[member])

        console.log(this.state)
    }
    handlePasswordChange(event){
        this.setState({fadePwIn: true})
        this.fillLogin(event)
    }

    fillAddress(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let member = this.state.member;
        member.address[name] = value;
        this.setState(this.state[member])
    }

    fillLogin(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let member = this.state.member;
        member.login[name] = value;
        this.setState(this.state[member])
    }

    checkPasswordMatching(event){
        const password = this.state.member.login.password;
        if (password === event.target.value){
            this.setState({matchingPasswords: true})
        }else{
            this.setState({matchingPasswords: false})
        }
    }

    sendData() {
        const requestOptions = {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(this.state.member)
        };
        fetch("/v1/member", requestOptions)
            .then((response) => {
                if (response.status === 200) { 
                    console.log("SUCCESSS")
                    this.alertState = ({
                        success: true,
                        warning: false,
                        error: false,
                        warning_mail: false,
                        warning_name: false
                    })
                    this.setState({requestFailed: false})

                }
                else if (response.status === 500) { //Eingabe nicht korrekt, PLZ ist String keine Zahl etc.
                    console.log("inkorrekte Eingabe")
                    this.alertState = ({success: false,
                        warning:false,
                        error:true,
                        warning_mail: false,
                        warning_name: false})
                    this.setState({requestFailed: true})
            }
            else if (response.status === 403) { //Email schon vergeben
                console.log("Email bereits vergeben")
                this.alertState = ({success: false,
                    warning:false,
                    error:false,
                    warning_mail: true,
                    warning_name: false})
                this.setState({requestFailed: true})
            }
            else if (response.status === 406) { //Name schon vergeben
                console.log("Name bereits vergeben")
                this.alertState = ({success: false,
                    warning:false,
                    error:false,
                    warning_mail: false,
                    warning_name: true})
                this.setState({requestFailed: true})
            }
            else{
                console.log("Ein Fehler ist aufgetreten")
                this.alertState = ({success: false,
                    warning:true,
                    error:false,
                    warning_mail: false,
                    warning_name: false})
                this.setState({ requestFailed: true })

            }
        })
        window.location.reload()
    }

    render() {
        const daten = this.state.member

        return (
            <Container style={{marginTop: "1%", marginBottom: "1%"}}>
            <h2>Konto</h2>
                <Form>
                    <br/>
                    <Label>Persönliche Daten</Label>
                    <br/><br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Vorname</InputGroupText>
                        </InputGroupAddon>
                        <Input name="firstName" defaultValue={daten.firstName} placeholder={daten.firstName} onChange={event => this.handleChange(event)}/>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "1%"}}>
                            <InputGroupText>Nachname</InputGroupText>
                        </InputGroupAddon>
                        <Input name="lastName" defaultValue={daten.lastName} placeholder={daten.lastName} onChange={event => this.handleChange(event)}/>
                    </InputGroup>
                    <br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Telefon</InputGroupText>
                        </InputGroupAddon>
                        <Input name="phone" defaultValue={daten.phone} placeholder={daten.phone} onChange={event => this.handleChange(event)}/>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "1%"}}>
                            <InputGroupText>Geburtsdatum</InputGroupText>
                        </InputGroupAddon>
                        <Input name="dateOfBirth"
                               defaultValue={daten.dateOfBirth}
                               disabled
                        />
                    </InputGroup>
                    <br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Email</InputGroupText>
                        </InputGroupAddon>
                        <Input name="email" type="email" defaultValue={daten.login.email} placeholder={daten.login.email} onChange={event => this.fillLogin(event)}/>
                    </InputGroup>
                    <br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>IBAN</InputGroupText>
                        </InputGroupAddon>
                        <Input name="iban" defaultValue={daten.iban} placeholder={daten.iban} onChange={event => this.handleChange(event)}/>
                    </InputGroup>
                    <br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Führerscheinnummer</InputGroupText>
                        </InputGroupAddon>
                        <Input name="drivingLicenseNumber" defaultValue={daten.drivingLicenseNumber} placeholder={daten.drivingLicenseNumber} onChange={event => this.handleChange(event)}/>
                    </InputGroup>
                    <br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Benutzername</InputGroupText>
                        </InputGroupAddon>
                        <Input name="username" disabled placeholder={daten.login.username} />
                    </InputGroup>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Password</InputGroupText>
                        </InputGroupAddon>
                        <Input name="password" type="password" placeholder="*****" onChange={event => this.handlePasswordChange(event)}/>

                    </InputGroup>
                    <InputGroup>
                        <Fade in={this.state.fadePwIn} style={{width: "100%"}}>
                            <Input id="wdhPw" type="password" placeholder="wiederhole Password" valid={this.state.matchingPasswords} invalid={!this.state.matchingPasswords} onChange={event => this.checkPasswordMatching(event)}/>
                            <FormFeedback tooltip>Oh nein! Das Password stimmt nicht überein</FormFeedback>
                        </Fade>
                    </InputGroup>
                    <br/>

                    <hr className="my-2"/>
                    <br/>
                    <Label>Adresse</Label>
                    <br/><br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Straße</InputGroupText>
                        </InputGroupAddon>
                        <Input name="street" defaultValue={daten.address.street} placeholder={daten.address.street} onChange={event => this.fillAddress(event)}/>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "1%"}}>
                            <InputGroupText>Hausnr.</InputGroupText>
                        </InputGroupAddon>
                        <Input name="houseno" defaultValue={daten.address.houseno} placeholder={daten.address.houseno} onChange={event => this.fillAddress(event)}/>
                    </InputGroup>
                    <br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Postleitzahl</InputGroupText>
                        </InputGroupAddon>
                        <Input name="postcode" defaultValue={daten.address.postcode} placeholder={daten.address.postcode} onChange={event => this.fillAddress(event)}/>
                        <InputGroupAddon addonType="prepend" style={{marginLeft: "1%"}}>
                            <InputGroupText>Ort</InputGroupText>
                        </InputGroupAddon>
                        <Input name="city" defaultValue={daten.address.city} placeholder={daten.address.city} onChange={event => this.fillAddress(event)}/>
                    </InputGroup>
                    <br/><br/>
                    <hr className="my-2"/>
                    <br/>
                    <Label>System</Label>
                    <br/><br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>RFID</InputGroupText>
                        </InputGroupAddon>
                        <Input disabled placeholder={daten.rfid.chipNumber}/>
                    </InputGroup><br/>
                    <InputGroup>
                        <InputGroupAddon addonType="prepend">
                            <InputGroupText>Tarif</InputGroupText>
                        </InputGroupAddon>
                        <Input disabled placeholder={daten.tariff.bezeichner} style={{width: "70%"}}/>
                        <Input disabled placeholder={daten.tariff.prozentsatz + "%"} style={{width: "20%"}}/>
                    </InputGroup>

                    <br/><br/>
                    <Button color="primary" style={{align: "right"}} onClick={(() => this.sendData())}>Speichern</Button>
                </Form>
            </Container>
        );
    }


}

export default Konto;