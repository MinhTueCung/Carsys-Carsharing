import React from "react"
import {ApplicationStore} from "./ApplicationStore"
import {BookingStore} from "./BookingStore"

const storeContext = (React.createContext({
    bookingStore: new BookingStore(),
    applicationStore: new ApplicationStore()
}))

export const useStore = () => (React.useContext(storeContext))