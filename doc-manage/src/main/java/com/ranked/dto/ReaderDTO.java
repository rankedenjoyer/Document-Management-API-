package com.ranked.dto;

public class ReaderDTO {
    private String id;
    private String kindOfReader;
    private String email;
    private String phoneNumber;
    private String personId; // Use personId instead of Person

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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
