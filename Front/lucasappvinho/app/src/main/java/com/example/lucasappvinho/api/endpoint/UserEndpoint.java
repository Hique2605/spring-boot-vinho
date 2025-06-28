package com.example.lucasappvinho.api.endpoint;

import com.example.lucasappvinho.api.model.User;
import com.example.lucasappvinho.api.model.Vinho;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserEndpoint {

    @GET("users")
    Call<List<User>> getAllUsers();

    @GET("users/{id}")
    Call<User> findById(@Path("id") Long id);

    @GET("users/buscarPorEmail/{email}")
    Call<User> findByEmail(@Path("email") String email);

    @POST("users")
    Call<User> insert(@Body User user);

    @PUT("users/{id}")
    Call<User> update(@Path("id") Long id, @Body User user);
}
