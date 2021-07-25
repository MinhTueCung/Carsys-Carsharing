package com.carsys.carsharing.persistanceLayer.model;

import org.threeten.bp.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value="EMPLOYEE")
public class Employee extends User {


    public Employee() {
        super();
    }

    public Employee(String lastName, String firstName, String date_of_birth, Login login, Address address, boolean aktiv, String telefone, CarStation carstation) {
        super(lastName, firstName, date_of_birth, login, address, aktiv, telefone);

    }



}