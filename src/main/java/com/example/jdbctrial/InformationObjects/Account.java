package com.example.jdbctrial.InformationObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    String username;
    Integer balance;
    String password;
    String email;

    public Account(){}

    public Account(String username,
                   Integer balance,
                   String password,
                   String email) {
        this.username = username;
        this.balance = balance;
        this.password = password;
        this.email=email;
    }

    public String getUsername() {
        return username;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
