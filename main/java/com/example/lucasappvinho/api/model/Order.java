package com.example.lucasappvinho.api.model;

import com.example.lucasappvinho.api.model.Enums.OrderStatus;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {

    private Long id;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private String moment;

    private String orderStatus;

    private User client;

    private Representante representante;

    private Set<OrderItem> items = new HashSet<>();

    private Payment payment;

    public Order() {

    }

    public Order(Long id, String moment, String orderStatus, User client, Representante representante) {
        super();
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
        this.representante = representante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    public void setRepresentanteId(Long idUsuarioLogado) {
    }

    public void setItems(List<OrderItem> itensPedido) {

    }
}