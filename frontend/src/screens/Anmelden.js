import React, {useState} from "react";
import {useHistory} from "react-router-dom";
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';


function Anmelden() {
    const history = useHistory();

    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");

    async function handleSubmit() {
        await fetch("/v1/login",
            {
                method: 'POST',
                headers:
                    {
                        "Authorization": 'Basic ' + window.btoa(username + ":" + password)
                    }
            }).then(history.push("/"))
        window.location.reload()
    }

    return (
        <div>
        <Container fluid><h2>Anmelden</h2></Container>
        <Container>
            <br/>
            <Form inline onSubmit={handleSubmit} style={{marginLeft: "1%", width: "40%"}}>
                <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
                    <Label for="idUsername" className="mr-sm-2">Username</Label>
                    <Input type="username" name="username" id="idUsername" placeholder="" value={username}
                           onChange={(e) => setUsername(e.target.value)}/>
                </FormGroup>
                <br/>
                <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
                    <Label for="idPassword" className="mr-sm-2">Password</Label>
                    <Input type="password" name="password" id="idPassword" placeholder="" value={password}
                           onChange={(e) => setPassword(e.target.value)}/>
                </FormGroup>
                <br/>
                <Button color="primary" type="submit">Anmelden </Button>
            </Form>
        </Container>
        </div>
    );
}

export default Anmelden;