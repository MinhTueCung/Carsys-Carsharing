import React, {useState} from "react"
import {useStore} from "../stores/Store"
import DatePicker from "react-datepicker"
import {useHistory} from 'react-router-dom'
import "react-datepicker/dist/react-datepicker.css"
import { Button, Label, FormGroup } from 'reactstrap'
import TimePicker from 'react-time-picker'
import "./booking.css"
import { observer } from "mobx-react-lite"

const BookingSide = ({car, abbrechen, bestaetigen, bookingStore, infoText}) => {

    const [startDate, setStartDate] = useState(null)
    const [endDate, setEndDate] = useState(null)
    const [starttime, setStarttime] = useState("00:00")
    const [endtime, setEndtime] = useState("00:00")

    const startDateAction = (date) => {
        setStartDate(date)
        bookingStore.setStartDate(date)
        console.log(bookingStore.booking.startDate)
    }

    const endDateAction = (date) => {
        setEndDate(date)
        bookingStore.setEndDate(date)
        console.log(bookingStore.booking.endDate)
    }

    const startTimeAction = (time) => {
        setStarttime(time)
        bookingStore.setStartTime(time)
        console.log(bookingStore.booking.startTime)
    }

    const endTimeAction = (time) => {
        setEndtime(time)
        bookingStore.setEndTime(time)
        console.log(bookingStore.booking.endTime)
    }

    return (
        <div className="booking-content-flex-column">
            <div className="booking-content-flex-row">
                <CarData className="flex-row-item" car={car}/>
                <FormGroup className="flex-row-item booking-content-flex-column">
                    <Label
                        for="startDate"
                        style={{
                            marginLeft: 30,
                            fontSize: 20
                        }}>Startdatum</Label>
                    <DatePicker selected={startDate}
                                onChange={date => startDateAction(date)}
                                dateFormat="dd/MM/yyyy"/>
                    <Label
                        for="startTime"
                        style={{
                            marginTop: 20,
                            marginLeft: 30,
                            fontSize: 20
                        }}>Startzeit</Label>
                    <TimePicker
                        onChange={time => startTimeAction(time)}
                        maxTime="23:59"
                        value={starttime}
                    />
                    <Label
                        for="endDate"
                        style={{
                            marginTop: 40,
                            marginLeft: 30,
                            fontSize: 20
                        }}>Enddatum</Label>
                    <DatePicker selected={endDate}
                                onChange={date => endDateAction(date)}
                                dateFormat="dd/MM/yyyy"/>
                    <Label
                        for="endTime"
                        style={{
                            marginTop: 20,
                            marginLeft: 30,
                            fontSize: 20
                        }}>Endzeit</Label>
                    <TimePicker
                        onChange={time => endTimeAction(time)}
                        maxTime="23:59"
                        value={endtime}
                    />
                </FormGroup>
            </div>
            <div className="info-text">
                {infoText.length === 0
                  ? null
                  : <p>{infoText}</p>}
            </div>
            <div className="flex-column-item booking-content-flex-row">
                <Button className="button"  onClick={() => abbrechen()} color="primary">
                    Abbrechen
                </Button>
                <Button className="button" onClick={() => bestaetigen()} color="primary">
                    Bestaetigen
                </Button>
            </div>
        </div>
    )
}

const ImageSide = ({car}) => {
    return (
        <img src={require("../assets/images/"+ car.description +".png")} alt={"../assets/images/ford_focus.png"} width="350" height="250"/>
    )
}

const CarData = ({car}) => {
    return (
        <div>
            <ul>
                <li><strong>Fahrzeug</strong>: {car.description}</li>
                <li><strong>Station</strong>: {car.carstationCityName}</li>
                <li><strong>Typ</strong>: {car.type}</li>
                <li><strong>Kategorie</strong>: {car.category}</li>
                <li><strong>Typ</strong>: {car.transmission}</li>
                <li><strong>Sitze</strong>: {car.seats}</li>
                <li><strong>Transmission</strong>: {car.licensePlate}</li>
                <li><strong>Kilometerstand</strong>: {car.mileage}</li>
                <li><strong>Verbauch</strong>: {car.consumption}</li>
                <li><strong>Ausleihkosten Pro Std</strong>: {car.pricePerHour} €</li>
            </ul>
        </div>
    )
}


export const Booking = observer(() => {

    const {bookingStore} = useStore()

    const [infoText, setInfoText] = useState("")

    const history = useHistory()

    const abbrechen = () => {
        history.push("/Fahrzeuge")
        bookingStore.resetBooking()
    }

    const abschicken = () => {
        bookingStore.setBookingCarId()
        bookingStore.setBookingStatus()

        const requestOptions = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bookingStore.booking)
        }
        fetch("/v1/bookings/else", requestOptions) 
            .then(function (response) {
                return response.text()
            })
            .then(data => {
                console.log("Response from backend", data)
                if(data.includes("401")){
                    setInfoText("Sie müssen sich anmelden, um eine Buchung betätigen zu können")
                }
                else{
                    setInfoText(data)
                } 
                console.log(infoText)
            })
    }

    const checkStartDateAndEndDate = () => {
        var startDateArray = bookingStore.booking.startDate.split(".")
        var startTimeArray = bookingStore.booking.startTime.split(":")
        var startDateInDatesSum = parseInt(startDateArray[0]) + parseInt(startDateArray[1]) * 30 + parseInt(startDateArray[2]) * 365
        var startTimeInMinutesSum = parseInt(startTimeArray[0]) * 60 + parseInt(startTimeArray[1])
        var endDateArray = bookingStore.booking.endDate.split(".")
        var endTimeArray = bookingStore.booking.endTime.split(":")
        var endDateInDatesSum = parseInt(endDateArray[0]) + parseInt(endDateArray[1]) * 30 + parseInt(endDateArray[2] * 365)
        var endTimeInMinutesSum = parseInt(endTimeArray[0]) * 60 + parseInt(endTimeArray[1])

        if(startDateInDatesSum > endDateInDatesSum){
            setInfoText("Enddatum darf nicht vor dem Startdatum sein")
        } else {
            if(startDateInDatesSum === endDateInDatesSum){
                if(startTimeInMinutesSum >= endTimeInMinutesSum){
                    setInfoText("Endzeit darf nicht vor oder gleich der Startzeit sein")
                } else {
                    if(startTimeInMinutesSum > endTimeInMinutesSum - 30){
                        setInfoText("Die Mindestausleihdauer muss mindestens 30 Min betragen")
                    } else {
                        abschicken()
                    }
                }
            }
            else {
                abschicken()
            }
        }
    }

    const bestaetigen = () => {
        console.log("Start Date ", bookingStore.booking.startDate)
        console.log("End Date ", bookingStore.booking.endDate)
        console.log("Booking ", bookingStore.booking)
        var todayDate = new Date()
        var todayInDatesSum = parseInt(todayDate.getDate() + (todayDate.getMonth() + 1) * 30 + todayDate.getFullYear() * 365)
        console.log("Today in Dates Sum ", todayInDatesSum)
        var todayTimeInMinutesSum = parseInt(todayDate.getHours() * 60 + todayDate.getMinutes())
        console.log("Today in Minutes Sum ", todayTimeInMinutesSum)
        if(bookingStore.booking.startDate.length === 0 || bookingStore.booking.endDate.length === 0){
            setInfoText("Unvollständige Angabe")
        } else {
            var startDateArray = bookingStore.booking.startDate.split(".")
            var startTimeArray = bookingStore.booking.startTime.split(":")
            console.log("Start Day", startDateArray[0])
            console.log("Start Month", startDateArray[1])
            console.log("Start Year", startDateArray[2])
            var startDateInDatesSum = parseInt(startDateArray[0]) + parseInt(startDateArray[1]) * 30 + parseInt(startDateArray[2]) * 365
            console.log("Start in Dates Sum ", startDateInDatesSum)
            var startTimeInMinutesSum = parseInt(startTimeArray[0]) * 60 + parseInt(startTimeArray[1])
            if(startDateInDatesSum < todayInDatesSum){
                setInfoText("Das Startdatum liegt in der Vergangenheit")
            } else {
                if(startDateInDatesSum === todayInDatesSum){
                    if(startTimeInMinutesSum <= todayTimeInMinutesSum){
                        setInfoText("Die Startzeit liegt in der Vergangenheit")
                    } else {
                        checkStartDateAndEndDate()
                    }
                } else {
                    checkStartDateAndEndDate()
                }
            }   
        }   
    }

    return (
        <div className="booking-content-flex-column">
            <div className="booking-content-flex-row">
                <ImageSide className="flex-row-item" car={bookingStore.chosenCar}/>
                <BookingSide className="flex-row-item" car={bookingStore.chosenCar} abbrechen={() => abbrechen()}
                             bestaetigen={() => bestaetigen()} bookingStore={bookingStore} infoText={infoText}/>
            </div>
        </div>
    )
})
