package com.example.lucasappvinho.api.endpoint;

import com.example.lucasappvinho.api.model.Admin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface AdminEndpoint {
    @GET("admins/buscarPorEmail/{email}")
    Call<Admin> findByEmail(@Path("email") String email);

    @POST("admins")
    Call<Admin> insert(@Body Admin admin);
}

