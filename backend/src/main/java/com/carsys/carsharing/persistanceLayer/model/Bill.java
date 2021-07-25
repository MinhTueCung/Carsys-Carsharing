package com.carsys.carsharing.persistanceLayer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.UUID;

@Table(name="bills")
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "payed")
    private boolean payed;

    @Column(name = "price")
    private float price;

    @OneToOne
    @JoinColumn(name="bookingId",referencedColumnName = "id")
    private Booking booking;

    @Column
    private Date dueTo;


    public Bill() {
    }

    public Bill(boolean payed, float price, Booking booking, Date dueTo){
        this.payed = payed;
        this.price = price;
        this.booking = booking;
        this.dueTo=dueTo;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Date getDueTo() {
        return dueTo;
    }

    public void setDueTo(Date dueTo) {
        this.dueTo = dueTo;
    }

    public UUID getId() {
        return id;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
