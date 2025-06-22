package com.example.lucasappvinho.api.model;

import com.example.lucasappvinho.api.model.Enums.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private UserType tipo;
    private String name;
    private String email;
    private String phone;
    private String endereco;
    private String password;

    private List<Order> orders = new ArrayList<>();

    public User(String name, String email, String phone, String endereco, String password) {
        this.tipo = UserType.valueOf("USER"); // fixo
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.endereco = endereco;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getTipo() {
        return tipo;
    }

    public void setTipo(UserType tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
