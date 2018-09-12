package com.example.jdbctrial.InformationObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private String email;
    private Integer age;
    private String sex;
    private String contactNo;
    private java.sql.Date dob;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getContactNo() {
        return contactNo;
    }

    public java.sql.Date getDob() {
        return dob;
    }

    public User(String name,
                String email,
                Integer age,
                String sex,
                String contactNo,
                java.sql.Date dob) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.contactNo = contactNo;
        this.dob = dob;
    }
}
