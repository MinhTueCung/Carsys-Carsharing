package com.carsys.carsharing.persistanceLayer.model;


import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "carstation")
public class CarStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} ) // Nachschauen!
    @JoinColumn(name="addressid",referencedColumnName = "id")
    private Address address;

    public CarStation() {
    }

    public CarStation(Address adress) {
        this.address = adress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
