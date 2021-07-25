package com.carsys.carsharing.persistanceLayer.model;

import java.util.UUID;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private FuelAircondition fuelAircondition;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(name = "gps")
    private String gps;

    @OneToOne
    @JoinColumn(name="rfidId",referencedColumnName = "id")
    private RFID rfid;

    @OneToOne
    @JoinColumn(name="stationid",referencedColumnName = "id")
    private CarStation carstation;

    @Column(name = "licensePlate")
    private String licensePlate;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "maintenanceDate")
    private String maintenanceDate;

    @Column(name = "description")
    private String description;

    @Column(name = "pricePerHour")
    private float pricePerHour;

    @Column(name = "seats")
    private int seats;

    @Column(name="consumption")
    private float consumption;

    @Column(name = "active")
    private boolean active;


    public Car(Type type, String gps, RFID rfid, Transmission transmission, Float consumption, FuelAircondition fuelAircondition, CarStation carstation, Category category, String licensePlate, int mileage, String maintenanceDate, String description, int pricePerHour, boolean active) {
        this.type = type;
        this.gps = gps;
        this.rfid = rfid;
        this.carstation = carstation;
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.maintenanceDate = maintenanceDate;
        this.description = description;
        this.pricePerHour = pricePerHour;
        this.active = active;
        this.category = category;
        this.transmission = transmission;
        this.fuelAircondition = fuelAircondition;
        this.consumption = consumption;
    }

    public Car() {
    }

    public UUID getId() {
        return id;
    }



    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public FuelAircondition getFuelAircondition() {
        return fuelAircondition;
    }

    public void setFuelAircondition(FuelAircondition fuelAircondition) {
        this.fuelAircondition = fuelAircondition;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public RFID getRfid() {
        return rfid;
    }

    public void setRfid(RFID rfid_number) {
        this.rfid = rfid_number;
    }

    public CarStation getStation() {
        return carstation;
    }

    public void setStation(CarStation station) {
        this.carstation = station;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String license_plate) {
        this.licensePlate = license_plate;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(String maintenance_date) {
        this.maintenanceDate = maintenance_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float renting_cost_per_hour) {
        this.pricePerHour = renting_cost_per_hour;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}