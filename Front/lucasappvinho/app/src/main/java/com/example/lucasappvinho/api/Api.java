package com.example.lucasappvinho.api;

import com.example.lucasappvinho.api.endpoint.AdminEndpoint;
import com.example.lucasappvinho.api.endpoint.OrderEndpoint;
import com.example.lucasappvinho.api.endpoint.RepresentanteEndpoint;
import com.example.lucasappvinho.api.endpoint.UserEndpoint;
import com.example.lucasappvinho.api.endpoint.VinhoEndpoint;
import com.example.lucasappvinho.api.model.Admin;
import com.example.lucasappvinho.api.model.Representante;
import com.example.lucasappvinho.api.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    // URL para rodar no emulador Android
    public static final String URL_ROOT = "http://10.0.2.2:8080/";

    // Caso queira rodar no celular físico (troque o IP pelo da sua máquina)
    // public static final String URL_ROOT = "http://192.168.1.xxx:8080/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // ---------------- LOGIN ----------------

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

    // ---------------- ENDPOINTS ----------------

    public static UserEndpoint getUserEndpoint() {
        return retrofit.create(UserEndpoint.class);
    }

    public static AdminEndpoint getAdminEndpoint() {
        return retrofit.create(AdminEndpoint.class);
    }

    public static RepresentanteEndpoint getRepresentanteEndpoint() {
        return retrofit.create(RepresentanteEndpoint.class);
    }

    public static VinhoEndpoint getVinhoEndpoint() {
        return retrofit.create(VinhoEndpoint.class);
    }

    public static OrderEndpoint getOrderEndpoint() {
        return retrofit.create(OrderEndpoint.class);
    }
}