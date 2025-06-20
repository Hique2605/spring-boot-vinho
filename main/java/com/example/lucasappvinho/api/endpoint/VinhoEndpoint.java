package com.example.lucasappvinho.api.endpoint;

import com.example.lucasappvinho.api.model.Vinho;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface VinhoEndpoint {

    @GET("/vinhos")
    Call<List<Vinho>> getAllWines();

    @POST("vinhos")
    Call<Void> salvarVinho(@Body Vinho vinho);

}
