package com.example.lucasappvinho.api.model;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private Long id; // ou remova se não for necessário

    private Order order;
    private Vinho vinho;
    private Integer quantity;
    private Double price;

    public OrderItem() {}

    public OrderItem(Order order, Vinho vinho, Integer quantity, Double price) {
        this.order = order;
        this.vinho = vinho;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Vinho getVinho() {
        return vinho;
    }

    public void setVinho(Vinho vinho) {
        this.vinho = vinho;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
