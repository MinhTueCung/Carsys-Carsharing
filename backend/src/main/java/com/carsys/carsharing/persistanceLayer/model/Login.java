package com.carsys.carsharing.persistanceLayer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email; 

    public Login() {
    }
    
    public Login(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String nutzername) {
        this.username = nutzername;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String passwort) {
        if (passwort == null)
        {
            this.password = passwort;
        }
        else
        {
            this.password = BCrypt.hashpw(passwort, BCrypt.gensalt(12));
        }
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
    
