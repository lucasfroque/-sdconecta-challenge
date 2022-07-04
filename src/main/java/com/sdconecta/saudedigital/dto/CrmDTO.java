package com.sdconecta.saudedigital.dto;

import javax.persistence.Column;

public class CrmDTO {
    private String crm;
    private String uf;
    private String specialty;

    public CrmDTO(String crm, String uf, String specialty) {
        this.crm = crm;
        this.uf = uf;
        this.specialty = specialty;
    }

    public CrmDTO() {
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
}
