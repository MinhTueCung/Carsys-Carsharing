package com.carsys.carsharing.persistanceLayer.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@DiscriminatorValue(value="tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "identifier")
    private String identifier;
    @Column(name = "percentage")
    private String percentage;


    public Tariff(){

    }


    public Tariff(String identifier, String percentage) {
        this.identifier = identifier;
        this.percentage = percentage;
    }

    public UUID getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPercentage(String prozentsatz) {
        this.percentage = prozentsatz;
    }

    public void setIdentifier(String bezeichner) {
        this.identifier = bezeichner;
    }
}