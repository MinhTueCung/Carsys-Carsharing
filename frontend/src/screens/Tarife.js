import React, {useEffect, useState} from "react";
import {useHistory} from "react-router-dom";
import {Container, Card, CardBody, CardGroup, CardImg, CardSubtitle, CardText, CardTitle} from 'reactstrap';

function Tarife() {

    const [data, setData] = useState([]);
    const history = useHistory();

    const getData = () => {
        fetch("/v1/tariff"
            , {
                headers: {
                }
            }
        )
            .then(function (response) {
                if (!response.ok) {
                    history.push("/Anmelden");
                }
                return response.json();
            })
            .then(function (myJson) {
                setData(myJson)
            });
    }

    useEffect(() => {
        getData();
    }, [])// eslint-disable-line react-hooks/exhaustive-deps

    return (
        <Container fluid>
            <h2>Tarife</h2>
            <CardGroup>
                {data.map(data => (
                    <Card key={data.id}>
                        <CardImg top width="100%" src={require("../assets/images/" + data.bezeichner + ".jpg")} alt={data.id} style={{display: "block", margin: "auto", width: "40%"}}/>
                        <CardBody>
                            <CardTitle tag="h5">{data.bezeichner}</CardTitle>
                            <CardSubtitle tag="h6" className="mb-2 text-muted">Tarif</CardSubtitle>
                            <CardText>Mit diesem Tarif erhalten Sie {data.prozentsatz} % Nachlass auf Ihre
                                Buchungen!</CardText>
                        </CardBody>
                    </Card>

                ))}
            </CardGroup>
        </Container>
    );
}

export default Tarife;
