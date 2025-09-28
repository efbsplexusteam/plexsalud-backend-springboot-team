package com.plexsalud.plexsalud.auth.responses;

public class LoginResponse {
    private String accessToken;
    private String fullName;
    private Long id;

    private long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getId() {
        return id;
    }

    public LoginResponse setAccessToken(String token) {
        this.accessToken = token;
        return this;
    }

    public LoginResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public LoginResponse setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

}
