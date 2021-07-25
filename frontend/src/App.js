import React, {Component} from "react";
import {BrowserRouter as Router, Route} from "react-router-dom";
import "./icons.js";
import Abmelden from "./screens/Abmelden";
import Anmelden from "./screens/Anmelden";
import Daten from "./screens/Daten";
import {Fahrzeuge2} from "./screens/Fahrzeuge2";
import PasswortVergessen from "./screens/PasswortVergessen";
import {Booking} from "./screens/Booking";
import Registrieren from "./screens/Registrieren";
import Standorte from "./screens/Standorte";
import Startseite from "./screens/Startseite";
import Buchungen from "./screens/Buchungen";
import Tarife from "./screens/Tarife";
import "./style.css";
import NavigationBar from "./components/NavigationBar";
import Konto from "./screens/Konto"
import NavigationBarLoggedIn from "./components/NavigationBarLoggedIn";


class App extends Component {
    state =
        {
            loggedIn: false
        }

    getData() {
        fetch("/v1/login", {method: 'GET'})
            .then(response => response.json())
            .then(jsonData => this.setState(jsonData))
    }

    componentDidMount() {
        this.getData()
    }

    render() {
        const Fahrzeuge2Seite = () => {
            return (
                <Fahrzeuge2 loggedIn={this.state.loggedIn}/>
            )
        }

        const renderNavBars = () => {
            if (this.state.loggedIn === "false") {
                return <div>
                    <NavigationBar/>
                    <Router>
                        <Route path="/" exact component={Startseite}/>
                        <Route path="/Anmelden/" exact component={Anmelden}/>
                        <Route path="/Fahrzeuge/" exact component={Fahrzeuge2Seite}/>
                        <Route path="/PasswortVergessen/" exact component={PasswortVergessen}/>
                        <Route path="/Registrieren/" exact component={Registrieren}/>
                        <Route path="/Buchen/" exact component={Booking}/>
                        <Route path="/Standorte/" exact component={Standorte}/>
                        <Route path="/Startseite/" exact component={Startseite}/>
                        <Route path="/Tarife/" exact component={Tarife}/>
                    </Router>
                </div>;
            } else {
                return <div>
                    <NavigationBarLoggedIn/>
                    <Router>
                        <Route path="/" exact component={Startseite}/>
                        <Route path="/Abmelden/" exact component={Abmelden}/>
                        <Route path="/Daten/" exact component={Daten}/>
                        <Route path="/Fahrzeuge/" exact component={Fahrzeuge2Seite}/>
                        <Route path="/PasswortVergessen/" exact component={PasswortVergessen}/>
                        <Route path="/Buchen/" exact component={Booking}/>
                        <Route path="/Buchungen/" exact component={Buchungen}/>
                        <Route path="/Standorte/" exact component={Standorte}/>
                        <Route path="/Startseite/" exact component={Startseite}/>
                        <Route path="/Tarife/" exact component={Tarife}/>
                        <Route path="/Konto" exact component={Konto}/>
                    </Router>
                </div>;
            }
        }

        return (
            <div>
                {renderNavBars()}
            </div>
        );
    }
}

export default App;
