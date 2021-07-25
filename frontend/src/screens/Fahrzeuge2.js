import React, {useEffect, useState} from "react"
import {isEmpty, map} from "ramda"
import {CarsReactTable} from "../components/carsTable/CarsReactTable"
import { BookingButton } from "../components/carsTable/BookingButton"
import './fahrzeug2.css'
import {Container} from "reactstrap";


export const Fahrzeuge2 = () => {

    const [cars, setCars] = useState([])

    const COLUMNS = [
        {Header: "", accessor: "Image"},
        {Header: "Fahrzeug", accessor: "Fahrzeug"},
        {Header: "Typ", accessor: "Typ"},
        {Header: "Kennzeichen", accessor: "Kennzeichen"},
        {Header: "Verbrauch", accessor: "Verbrauch"},
        {Header: "Kilometerstand", accessor: "Kilometerstand"},
        {Header: "Kategorie", accessor: "Kategorie"},
        {Header: "Transmission", accessor: "Transmission"},
        {Header: "Ausleihkosten pro Std (€)", accessor: "Ausleihkosten_Pro_Std"},
        {Header: "Station", accessor: "Station"},
        {Header: "Sitze", accessor: "Sitze"},
        {Header: "Buchen", accessor: "Buchen"}
    ]

    useEffect(() => {
        getAllCars();
    }, [])

    const DATA = map(car => (
        {
            Image: <img src={require("../assets/images/"+ car.description +".png")} alt={"img"} width="96" height="65"/>,
            Fahrzeug: car.description,
            Typ: car.type,
            Kennzeichen: car.licensePlate,
            Verbrauch: car.consumption,
            Kilometerstand: car.mileage,
            Kategorie: car.category,
            Transmission: car.transmission,
            Ausleihkosten_Pro_Std: car.pricePerHour,
            Station: car.carstationCityName,
            Sitze: car.seats,

            Buchen: <BookingButton color="primary" chosenCar={car}
                            carImagePath={"src/assets/images/ford_fiesta.png"}>Senden</BookingButton>

        }
    ), cars)

    const getAllCars = () => {
        const requestOptions = {
            method: "GET",
            headers: {
                'Accept': 'application/json'
            }
        };
        fetch("/v1/cars", requestOptions)
            .then(function (response) {
                const data = response.json()
                console.log(data)

                return data;
            })
            .then(function (data) {
                console.log(data)
                setCars(data)
            })
    }

    return (
        <Container fluid>
            <div className="title-content-flex">
                <h2>Fahrzeuge</h2>
            </div>
            <div className="fahrzeug2-content-flex">
                <div className="flex-item">
                    <CarsReactTable
                        COLUMNS={COLUMNS}
                        DATA={DATA}
                        {...isEmpty(cars) && {EmptyComponent: <p>No Car Error</p>}}
                    />
                </div>
            </div>
        </Container>
    )
}


  