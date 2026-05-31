package com.Authmicroservice.Authmicroservice.models;

public class JwtTokenResponseDto {

    private String token;
    private String type;
    private String validUntil;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "JwtTokenResponseDto{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", validUntil='" + validUntil + '\'' +
                '}';
    }
}
