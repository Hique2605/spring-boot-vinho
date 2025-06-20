package com.example.lucasappvinho.api.endpoint;

import com.example.lucasappvinho.api.model.Order;
import com.example.lucasappvinho.api.model.Vinho;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderEndpoint {

    @GET("/orders")
    Call<List<Order>> getAllorder();


    @GET("/orders/users/{clientId}")
    Call<List<Order>> getPedidosByUser(@Path("clientId") Long clientId);

    // Criar um novo pedido (para o Representante)
    @POST("/orders")
    Call<Order> criarPedido(@Body Order novoPedido);

}
