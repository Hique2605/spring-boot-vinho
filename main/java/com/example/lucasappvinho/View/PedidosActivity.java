package com.example.lucasappvinho.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.Sessao;
import com.example.lucasappvinho.View.Admin.PainelAdmActivity;
import com.example.lucasappvinho.View.Representante.DeletarPedidoActivity;
import com.example.lucasappvinho.View.Representante.RepresentantePedidoActivity;
import com.example.lucasappvinho.adapter.OrderAdapter;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Order;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList = new ArrayList<>();
    private ImageButton btnVoltar;
    private ImageButton btnVoltarOculto;

    private Button btnNovoPedido, btnDeletarPedido;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pedidos);
        configurarPorTipoUsuario();

        recyclerView = findViewById(R.id.recyclerPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);

        // Botão de voltar para home
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(PedidosActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // Botão de voltar para painel
        btnVoltarOculto = findViewById(R.id.btnVoltarOculto);
        btnVoltarOculto.setOnClickListener(view -> {
            Intent intent = new Intent(PedidosActivity.this, PainelAdmActivity.class);
            startActivity(intent);
            finish();
        });

        //botão novo pedido
        btnNovoPedido = findViewById(R.id.btnNovoPedido);
        btnNovoPedido.setOnClickListener(view -> {
            Intent intent = new Intent(PedidosActivity.this, RepresentantePedidoActivity.class);
            startActivity(intent);
        });

        //botão deletar pedido
        btnDeletarPedido = findViewById(R.id.btnDeletarPedido);
        btnDeletarPedido.setOnClickListener(view -> {
            Intent intent = new Intent(PedidosActivity.this, DeletarPedidoActivity.class);
            startActivity(intent);
        });

        carregarPedidos();
    }

    private void carregarPedidos() {
        Call<List<Order>> call = Api.getOrderEndpoint().getAllorder();

        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orderList.clear();
                    orderList.addAll(response.body());
                    orderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(PedidosActivity.this, "Erro ao carregar pedidos", Toast.LENGTH_SHORT).show();
                    Log.e("API", "Resposta vazia ou erro: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(PedidosActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API", "Falha: ", t);
            }
        });

    }

    private void configurarPorTipoUsuario() {
        boolean isAdmin = Sessao.tipoUsuario.equals("admin");
        boolean isUser = Sessao.tipoUsuario.equals("user");


        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltarOculto = findViewById(R.id.btnVoltarOculto);

        if (isAdmin) {
           btnVoltar.setVisibility(View.GONE);
           btnVoltarOculto.setVisibility(View.VISIBLE);

        } else if (isUser) {
            //user nao tem acesso aqui, ele é outra tela , tela_meus_pedidos
        } else {
            btnVoltar.setVisibility(View.VISIBLE);
            btnVoltarOculto.setVisibility(View.GONE);
        }

    }

}