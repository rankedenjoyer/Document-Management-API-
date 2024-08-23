package com.ranked.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reader {
    @Id
    private String id;
    private String kindOfReader;
    private String email;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKindOfReader() {
        return kindOfReader;
    }

    public void setKindOfReader(String kindOfReader) {
        this.kindOfReader = kindOfReader;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}


