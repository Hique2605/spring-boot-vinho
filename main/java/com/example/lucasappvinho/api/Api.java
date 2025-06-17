package com.example.lucasappvinho.api;

import com.example.lucasappvinho.api.endpoint.AdminEndpoint;
import com.example.lucasappvinho.api.endpoint.RepresentanteEndpoint;
import com.example.lucasappvinho.api.endpoint.UserEndpoint;
import com.example.lucasappvinho.api.endpoint.VinhoEndpoint;
import com.example.lucasappvinho.api.model.Admin;
import com.example.lucasappvinho.api.model.Representante;
import com.example.lucasappvinho.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static final String URL_ROOT = "http://10.0.2.2:8080/";  //para rodar no emulador

    //para rodar no celular
    // public static final String URL_ROOT = "http://192.168.1.104:8080/";
    //rever ip sempre ipconfig
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void findByEmailUser(final String email, final Callback<User> callback) {
        UserEndpoint endpoint = retrofit.create(UserEndpoint.class);
        Call<User> call = endpoint.findByEmail(email);
        call.enqueue(callback);
    }

    public static void findByEmailAdmin(final String email, final Callback<Admin> callback) {
        AdminEndpoint endpoint = retrofit.create(AdminEndpoint.class);
        Call<Admin> call = endpoint.findByEmail(email);
        call.enqueue(callback);
    }

    public static void findByEmailRepresentante(final String email, final Callback<Representante> callback) {
        RepresentanteEndpoint endpoint = retrofit.create(RepresentanteEndpoint.class);
        Call<Representante> call = endpoint.findByEmail(email);
        call.enqueue(callback);
    }




    // Retorna a instância do endpoint User
    public static UserEndpoint getUserEndpoint() {
        return retrofit.create(UserEndpoint.class);
    }

    // Retorna a instância do endpoint Admin
    public static AdminEndpoint getAdminEndpoint() {
        return retrofit.create(AdminEndpoint.class);
    }

    // Retorna a instância do endpoint Representante
    public static RepresentanteEndpoint getRepresentanteEndpoint() {
        return retrofit.create(RepresentanteEndpoint.class);
    }

    // Retorna a instância do endpoint Vinho
    public static VinhoEndpoint getVinhoEndpoint() {
        return retrofit.create(VinhoEndpoint.class);
    }








}
