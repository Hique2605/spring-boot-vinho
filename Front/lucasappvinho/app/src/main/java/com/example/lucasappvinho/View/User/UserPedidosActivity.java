package com.example.lucasappvinho.View.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.Sessao;
import com.example.lucasappvinho.View.HomeActivity;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Order;
import com.example.lucasappvinho.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPedidosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList = new ArrayList<>();
    private ImageButton btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_meus_pedidos);

        recyclerView = findViewById(R.id.recyclerPedidosUsuario);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);


        btnSair = findViewById(R.id.btnSair);

        btnSair.setOnClickListener(view -> {
            Intent intent = new Intent(UserPedidosActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });



        carregarPedidos();
    }

    private void carregarPedidos() {
        Long userId = Sessao.idUsuarioLogado;

        Api.getOrderEndpoint().getPedidosByUser(userId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orderList.clear();
                    orderList.addAll(response.body());
                    orderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(UserPedidosActivity.this, "Nenhum pedido encontrado.", Toast.LENGTH_SHORT).show();
                    Log.e("API", "Erro: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(UserPedidosActivity.this, "Erro na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API", "Falha na API: ", t);
            }
        });
    }
}
