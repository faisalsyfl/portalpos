package com.ibnuputra.hitapiretrofit.model;

public class LoginCredentials {

    private String username;
    private String password;

    public LoginCredentials(String name, String pass) {
        this.username = name;
        this.password = pass;
    }
}