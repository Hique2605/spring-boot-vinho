package com.example.lucasappvinho.api.model;

import com.example.lucasappvinho.api.model.Enums.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Representante implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private UserType tipo;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String password;
    private Double meta;

    private List<Order> pedidos = new ArrayList<>();

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getMeta() {
        return meta;
    }

    public void setMeta(Double meta) {
        this.meta = meta;
    }
}
