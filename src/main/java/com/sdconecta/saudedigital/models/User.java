package com.sdconecta.saudedigital.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdconecta.saudedigital.models.enums.AuthorizationStatus;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String surname;
    @OneToMany(mappedBy = "user")
    private List<Crm> crms = new ArrayList<>();
    private String mobilePhone;
    @JsonIgnore
    private String roles = "USER";
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private AuthorizationStatus authorizationStatus = AuthorizationStatus.WAITING_FOR_AUTHORIZATION;

    public User() {
    }

    public User(Long id, String email, String password, String name, String surname, List<Crm> crms, String mobilePhone, String roles, AuthorizationStatus authorizationStatus) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.crms = crms;
        this.mobilePhone = mobilePhone;
        this.roles = roles;
        this.authorizationStatus = authorizationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AuthorizationStatus getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(AuthorizationStatus authorizationStatus) {
        if(this.authorizationStatus == null){
            this.authorizationStatus = AuthorizationStatus.WAITING_FOR_AUTHORIZATION;
        }else{
            this.authorizationStatus = authorizationStatus;
        }
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", crm=" + crms +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
