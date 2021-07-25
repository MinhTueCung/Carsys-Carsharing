import React, {Component} from "react";


class Address extends Component {

    render() {
        return <div>
            <p>{this.props.address.street} {this.props.address.houseno}</p>
            <p>{this.props.address.postcode} {this.props.address.city}</p>
        </div>
    }

}

export default Address;