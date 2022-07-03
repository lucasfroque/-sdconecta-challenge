package com.sdconecta.saudedigital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Crm {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String crm;
    @Column(length = 2)
    private String uf;
    private String specialty;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Crm() {
    }

    public Crm(Long id, String crm, String uf, String specialty, User user) {
        this.id = id;
        this.crm = crm;
        this.uf = uf;
        this.specialty = specialty;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", crm='" + crm + '\'' +
                ", uf='" + uf + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
