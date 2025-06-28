package com.example.lucasappvinho.View.Admin;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Order;
import com.example.lucasappvinho.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaRelatorioActivity extends AppCompatActivity {

    private EditText editIdRepresentante;
    private Button btnBuscarPedidos;

    private ImageButton btnVoltar;
    private RecyclerView recyclerPedidos;
    private OrderAdapter orderAdapter;
    private List<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_relatorio);

        // Botão de voltar para o painel
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            finish(); // Encerra a TelaRelatorioActivity e volta para a tela anterior
        });


        editIdRepresentante = findViewById(R.id.editIdRepresentante);
        btnBuscarPedidos = findViewById(R.id.btnBuscarPedidos);
        recyclerPedidos = findViewById(R.id.recyclerPedidos);

        recyclerPedidos.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(orderList);
        recyclerPedidos.setAdapter(orderAdapter);

        btnBuscarPedidos.setOnClickListener(view -> {
            String idStr = editIdRepresentante.getText().toString().trim();
            if (TextUtils.isEmpty(idStr)) {
                Toast.makeText(this, "Informe o ID do representante", Toast.LENGTH_SHORT).show();
                return;
            }

            Long id = Long.parseLong(idStr);
            buscarPedidosPorRepresentante(id);


        });
    }





    // Método para buscar pedidos por representante
    private void buscarPedidosPorRepresentante(Long representanteId) {
        Api.getOrderEndpoint().getPedidosByRepresentante(representanteId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orderList.clear();
                    orderList.addAll(response.body());
                    orderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(TelaRelatorioActivity.this, "Nenhum pedido encontrado.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(TelaRelatorioActivity.this, "Erro na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

