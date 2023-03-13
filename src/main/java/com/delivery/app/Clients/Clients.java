/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delivery.app.Clients;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@Entity
@Table(name = "clients")    
public class Clients implements Serializable {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long Id;
    
    @Column(name="name")
    private String Name;
    
    @Column(name="last_name")
    private String LastName;
    
    @Column(name="phone")
    private String Phone;
    
    @Column (name="email")
    private String Email;
    
    @Column(name="password")
    private String Password;

    public Clients(String Name, String LastName, String Phone, String Email, String Password) {
        this.Name = Name;
        this.LastName = LastName;
        this.Phone = Phone;
        this.Email = Email;
        this.Password = Password;
    }

    public Clients() {
    }
    
    

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    
    
    
   
}
