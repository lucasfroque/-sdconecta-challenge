package com.sdconecta.saudedigital.dto;


import com.sdconecta.saudedigital.models.Crm;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String email;
    private String password;
    private String name;
    private String surname;
    private List<Crm> crms = new ArrayList<>();
    private String mobilePhone;
    private String roles = "USER";


    public UserDTO(String email, String password, String name, String surname, List<Crm> crms, String mobilePhone, String roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.crms = crms;
        this.mobilePhone = mobilePhone;
        this.roles = roles;
    }

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Crm> getCrms() {
        return crms;
    }

    public void setCrms(List<Crm> crms) {
        this.crms = crms;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
