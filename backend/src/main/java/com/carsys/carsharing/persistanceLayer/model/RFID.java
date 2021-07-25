package com.carsys.carsharing.persistanceLayer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "RFID")
public class RFID {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "chipNumber")
    private String chipNumber;

    public RFID() {
    }

    public RFID(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getChipNumber() {
        return this.chipNumber;
    }

    public void setChipNumber(String chip_number) {
        this.chipNumber = chip_number;
    }
}
   
