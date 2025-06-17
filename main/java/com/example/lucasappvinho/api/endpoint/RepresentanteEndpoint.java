package com.example.lucasappvinho.api.endpoint;

import com.example.lucasappvinho.api.model.Representante;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RepresentanteEndpoint {
    @GET("representantes/buscarPorEmail/{email}")
    Call<Representante> findByEmail(@Path("email") String email);

    @POST("representantes")
    Call<Representante> insert(@Body Representante representante);

}
