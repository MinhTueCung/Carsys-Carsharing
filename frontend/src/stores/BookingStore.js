import {makeAutoObservable} from "mobx"

export class BookingStore {

    booking = {
        carId: "",
        startDate: "",
        startTime: "00:00",
        endDate: "",
        endTime: "00:00",
        status: ""
    }

    chosenCar = {
        id: "",
        type: "",
        mileage: 0,
        licensePlate: "",
        consumption: 0.0,
        dateOfService: null,
        pricePerHour: 0.0,
        description: "",
        active: true,
        category: "",
        transmission: "",
        fuelAC: null
    }

    carImagePath = ""

    constructor() {
        makeAutoObservable(this)
    }

    setBookingCarId = () => {
        this.booking.carId = this.chosenCar.id
        console.log("Good booking is", this.booking)
    }

    setBookingStatus = () => {
        this.booking.status = "RESERVED"
    }

    setChosenCar = ({chosenCar}) => {
        this.chosenCar = chosenCar
    }

    setStartTime = (time) => {
        this.booking.startTime = time
    }

    setEndTime = (time) => {
        this.booking.endTime = time
    }

    setInfoText = (infoText) => {
        this.infoText = infoText
    }

    setStartDate = (startDate) => {
        var startYear = startDate.getFullYear()
        var startMonth = startDate.getMonth() + 1 // Date() hat diesen Bug: Er spuckt eig. den Monat - 1 aus 
        console.log("Month", startMonth)
        var startDay = startDate.getDate()
        this.booking.startDate = startDay + "." + startMonth + "." + startYear
    }

    setEndDate = (endDate) => {
        var endYear = endDate.getFullYear()
        var endMonth = endDate.getMonth() + 1 // Date() hat diesen Bug: Er spuckt eig. den Monat - 1 aus 
        console.log("Month", endMonth)
        var endDay = endDate.getDate()
        this.booking.endDate = endDay + "." + endMonth + "." + endYear
    }

    setCarImagePath = (carImagePath) => {
        this.booking.carImagePath = carImagePath
    }

    resetBooking = () => {
        this.booking = {
            carId: "",
            startDate: "",
            startTime: "00:00",
            endDate: "",
            endTime: "00:00",
            status: ""
        }
    }
}