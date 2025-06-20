package com.example.lucasappvinho.api.endpoint;

import com.example.lucasappvinho.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserEndpoint {

    @GET("users")
    Call<List<User>> getAllUsers();


    @GET("users/buscarPorEmail/{email}")
    Call<User> findByEmail(@Path("email") String email);

    @POST("users")
    Call<User> insert(@Body User user);

}
