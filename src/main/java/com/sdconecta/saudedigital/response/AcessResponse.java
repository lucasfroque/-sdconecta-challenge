package com.sdconecta.saudedigital.response;

public class AcessResponse {
    private String access_token;
    private String refresh_token;
    private String authorization_status;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAuthorization_status() {
        return authorization_status;
    }

    public void setAuthorization_status(String authorization_status) {
        this.authorization_status = authorization_status;
    }
}
