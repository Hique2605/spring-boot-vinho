package com.example.lucasappvinho.api.endpoint;

import com.example.lucasappvinho.api.model.Representante;
import com.example.lucasappvinho.api.model.Vinho;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RepresentanteEndpoint {
    @GET("representantes/buscarPorEmail/{email}")
    Call<Representante> findByEmail(@Path("email") String email);

    @GET("representantes/{id}")
    Call<Representante> getById(@Path("id") Long id);

    @GET("representantes")
    Call<List<Representante>> getAllRepresentantes();
    @POST("representantes")
    Call<Representante> insert(@Body Representante representante);

    @PUT("representantes/{id}")
    Call<Representante> update(@Path("id") Long id, @Body Representante representante);

    @DELETE("representantes/{id}")
    Call<Void> deletarRepresentante(@Path("id") Long id);

}
