package com.carsys.carsharing.persistanceLayer.model;

import javax.persistence.*;
import java.util.UUID;

// Ausgewählte Vererbungsstrategie: SINGLE TABLE
// Warum: einfacher zu erkennen, welche Rolle ein User hat, durch "DiscriminatorColumn" 
// Vorheriger Ansatz: JOINED --> Übersichtlicher, aber höhere Komplexität beim Löschen, Abfragen,.... + langsamer!
@Entity
@Table(name = "users")  /// War "user", aber dieser Name ist für das System reserviert und darf nicht verwendet werden!
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="loginid",referencedColumnName = "id")
    private Login login;

    @ManyToOne
    @JoinColumn(name="addressid",referencedColumnName = "id")
    private Address address;

    @Column(name = "active")
    private boolean active;

    @Column(name = "phone")
    private String phone;

    public User(String lastName, String firstName, String dateOfBirth, Login login, Address address, boolean active, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.address = address;
        this.active = active;
        this.phone = phone;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String date_of_birth) {
        this.dateOfBirth = date_of_birth;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String telephone) {
        this.phone = telephone;
    }
}
