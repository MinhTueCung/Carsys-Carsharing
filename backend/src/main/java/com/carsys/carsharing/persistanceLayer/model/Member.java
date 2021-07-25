package com.carsys.carsharing.persistanceLayer.model;


import org.threeten.bp.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.UUID;

@Entity
@DiscriminatorValue(value="MEMBER")
public class Member extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name="tariffId",referencedColumnName = "id")
    private Tariff tariff;

    @OneToOne
    @JoinColumn(name="rfidId",referencedColumnName = "id")
    private RFID rfid;

    @Enumerated(EnumType.STRING)
    @Column(name = "tax", length = 20)
    private Tax tax; 
    
    @Column(name = "iban")
    private String iban;

    @Column(name = "authentificationStatus")
    private boolean authentificationStatus;  // "offen" || "abgeschlossen"

    @Column(name = "drivingLicenseNumber")
    private String drivingLicenseNumber;

    public Member() {
        super();
    }

    public Member(String lastName, String firstName, String date_of_birth, Login login, Address address, boolean active, String telephone, Tariff tariff, RFID rfid, Tax tax, String iban, boolean authentificationStatus, String drivingLicenseNumber) {
        super(lastName, firstName, date_of_birth, login, address, active, telephone);
        this.tariff = tariff;
        this.rfid = rfid;
        this.tax = tax;
        this.iban = iban;
        this.authentificationStatus = authentificationStatus;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RFID getRfid() {
        return this.rfid;
    }

    public void setRfid(RFID rfid_number) {
        this.rfid = rfid_number;
    }

    public Tax getTax() {
        return this.tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean isAuthentificationStatus() {
        return this.authentificationStatus;
    }

    public boolean getAuthentification_status() {
        return this.authentificationStatus;
    }

    public void setAuthentificationStatus(boolean authentification_status) {
        this.authentificationStatus = authentification_status;
    }

    public String getDrivingLicenseNumber() {
        return this.drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String driving_license_number) {
        this.drivingLicenseNumber = driving_license_number;
    }
}