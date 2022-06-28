package com.sdconecta.saudedigital.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    @OneToMany(mappedBy="user")
    private Set<Crm> crm;
    @Column(name = "mobile_phone")
    private String mobilePhone;

    public User() {
    }

    public User(Integer id, String email, String name, String surname, Set<Crm> crm, String mobilePhone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.crm = crm;
        this.mobilePhone = mobilePhone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Crm> getCrm() {
        return crm;
    }

    public void setCrm(Set<Crm> crm) {
        this.crm = crm;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
