package com.example.lucasappvinho.api.endpoint;

import com.example.lucasappvinho.api.model.Vinho;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface VinhoEndpoint {

    @GET("/vinhos")
    Call<List<Vinho>> getAllWines();

    @GET("/vinhos/{id}")
    Call<Vinho> findById(@Path("id") Long id);
    @POST("vinhos")
    Call<Void> salvarVinho(@Body Vinho vinho);

    @PUT("vinhos/{id}")
    Call<Vinho> updateVinho(@Path("id") Long id, @Body Vinho vinho);

    @DELETE("vinhos/{id}")
    Call<Void> deletarVinho(@Path("id") Long id);
}
