import React from "react"
import {useStore} from "../../stores/Store"
import {Button} from 'reactstrap'
import {useHistory} from 'react-router-dom'

export const BookingButton = (chosenCar, carImagePath, disabled) => {
    const {bookingStore} = useStore()

    const onClick = () => {
        bookingStore.setChosenCar(chosenCar)
        console.log("Chosen car", bookingStore.chosenCar)
        bookingStore.setCarImagePath(carImagePath)
        history.push("/Buchen")
    }

    const history = useHistory()

    return (
        <Button onClick={()=> onClick()} disabled={disabled} color="primary" >
            Buchen
        </Button>
    )
}

