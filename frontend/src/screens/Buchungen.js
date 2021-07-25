import React, {Component} from "react";
import {Button, Container, Fade, Table} from 'reactstrap';

class Buchungen extends Component {

    booking = {
        car: ""
    };

    constructor() {
        super();
        this.state = {
            bookings: [this.booking],
            car: [],
        }
    }


    async componentDidMount() {

        await fetch("/v1/bookings/else")
            .then(response => response.json())
            .then(jsonData => this.setState({bookings: jsonData}))


        for (const [index] of this.state.bookings.entries()) {
            await fetch("/v1/cars/" + this.state.bookings[index].carId)
                .then(response => response.json())
                .then((jsonData) => {
                    this.setState({car: jsonData});
                    this.booking = {...this.state.bookings[index]};
                    this.booking.car = this.state.car.description;
                    this.state.bookings[index] = this.booking;
                })

            this.setState({car: []}) //dient nur dazu, dass nach dem hizufügen von Car ind Booking neu gerendert wird
        }

    }

    sendCancel(bookingID) {
        fetch("/v1/bookings/" + bookingID + "?bookingID=" + bookingID,
            {
                method: 'DELETE',
            })
        window.location.reload()
    }

    render() {
        return (
            <Container fluid>
                <h2>Buchungen</h2>
                <Table>
                    <thead>
                    <tr>
                        <th>Nummer</th>
                        <th>Auto</th>
                        <th>Start-Datum</th>
                        <th>Start-Zeit</th>
                        <th>End-Datum</th>
                        <th>End-Zeit</th>
                        <th>Status</th>
                        <th>Aktion</th>
                    </tr>
                    </thead>
                    <tbody>

                    {this.state.bookings.map((booking, index) => (
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{booking.car}</td>
                            <td>{booking.startDate}</td>
                            <td>{booking.startTime}</td>
                            <td>{booking.endDate}</td>
                            <td>{booking.endTime}</td>
                            <td>{booking.status}</td>
                            <td>{<Fade in={booking.status === "RESERVED"}>
                                <Button color="primary" size="sm" onClick={() => {
                                    this.sendCancel(booking.id)
                                }}>STORNO</Button></Fade>}
                            </td>
                        </tr>
                    ))

                    }

                    </tbody>
                </Table>
            </Container>
        );
    }


}

export default Buchungen;
