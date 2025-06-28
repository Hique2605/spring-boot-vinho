package com.example.lucasappvinho.api.model;

import java.io.Serializable;
public class Pedido {
    private String titulo;
    private String descricao;
    private String valor;

    public Pedido(String titulo, String descricao, String valor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getValor() { return valor; }
}