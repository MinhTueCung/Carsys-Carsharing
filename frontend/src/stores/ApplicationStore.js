import {makeAutoObservable} from "mobx"

export class ApplicationStore {
    
    userIsLoggedIn

    constructor() {
        makeAutoObservable(this)
    }
    
    userLogOut = () => {
        this.userIsLoggedIn = false
    }

    userLogIn = () => {
        this.userIsLoggedIn = true
    }
}