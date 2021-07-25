import React from "react";
import {useHistory} from "react-router-dom";

function Abmelden() {
    const history = useHistory();

    async function handleSubmit() {


        await fetch("/logout", {
            method: 'GET',
        }).then(history.push("/Anmelden")).then(window.location.reload())

    }

    return (
        <div>
            {handleSubmit()}
        </div>
    );
}

export default Abmelden;
