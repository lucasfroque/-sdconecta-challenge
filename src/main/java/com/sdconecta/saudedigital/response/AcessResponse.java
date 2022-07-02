package com.sdconecta.saudedigital.response;

public class AcessResponse {
    private String access_token;
    private String eyJhbGciOiJIUzUxMiJ9;
    private String authorization_status;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getEyJhbGciOiJIUzUxMiJ9() {
        return eyJhbGciOiJIUzUxMiJ9;
    }

    public void setEyJhbGciOiJIUzUxMiJ9(String eyJhbGciOiJIUzUxMiJ9) {
        this.eyJhbGciOiJIUzUxMiJ9 = eyJhbGciOiJIUzUxMiJ9;
    }

    public String getAuthorization_status() {
        return authorization_status;
    }

    public void setAuthorization_status(String authorization_status) {
        this.authorization_status = authorization_status;
    }
}
