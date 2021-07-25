package com.carsys.carsharing.persistanceLayer.model;

import org.threeten.bp.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue(value="ADMIN")
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String lastName, String firstName, String date_of_birth, Login login, Address address, boolean aktiv, String telefone){
        super(lastName, firstName, date_of_birth, login, address, aktiv, telefone);
    }
}