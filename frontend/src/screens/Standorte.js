import React, {Component} from "react";
import {Container, Table} from "reactstrap";
import Address from "../components/Address";

class Standorte extends Component {
    state = {
        carStations: [],
    }

    componentDidMount() {

        fetch("/v1/carstation")
            .then(response => response.json())
            .then(jsonData => this.setState({carStations: jsonData}))
    }


    render() {

        return (
            <Container fluid>
                <h2 >Standorte</h2>
                <Table>
                    <thead>
                    <tr>
                        <th>Ort</th>
                        <th>Adresse</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.carStations.map(carStation => (
                        <tr key={carStation.id}>
                            <td>{carStation.address.city}</td>
                            <td><Address address={carStation.address}/></td>
                        </tr>
                    ))
                    }
                    </tbody>
                </Table>
            </Container>
        );
    }
}


export default Standorte;