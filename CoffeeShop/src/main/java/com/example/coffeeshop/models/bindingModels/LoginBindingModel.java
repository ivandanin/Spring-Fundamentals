package com.example.coffeeshop.models.bindingModels;

public class LoginBindingModel {

    private String username;
    private String password;

    public LoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public LoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
