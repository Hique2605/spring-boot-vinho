package com.example.lucasappvinho.api;

import com.example.lucasappvinho.api.endpoint.UserEndpoint;
import com.example.lucasappvinho.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static final String URL_ROOT = "http://10.0.2.2:8080/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void findByEmail(final String email, final Callback<User> callback) {
        UserEndpoint endpoint = retrofit.create(UserEndpoint.class);
        Call<User> call = endpoint.findByEmail(email);
        call.enqueue(callback);
    }


}
