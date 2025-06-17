package com.example.lucasappvinho.View.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.View.HomeActivity;
import com.example.lucasappvinho.api.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidosUsuarioActivity extends AppCompatActivity {

    private RecyclerView recyclerPedidosUsuario;

    private List<Pedido> listaPedidos;   // Sua classe de modelo Pedido

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pedidos_usuario);

        // Botão voltar
        ImageButton btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(PedidosUsuarioActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // opcional: encerra a TelaVinhosActivity para não acumular na pilha
        });


        // RecyclerView
        recyclerPedidosUsuario = findViewById(R.id.recyclerPedidosUsuario);
        recyclerPedidosUsuario.setLayoutManager(new LinearLayoutManager(this));

        // Simula lista de pedidos do usuário
        listaPedidos = getPedidosDoUsuario();

        // Adapter
       // pedidoAdapter = new PedidoAdapter(listaPedidos);
        //recyclerPedidosUsuario.setAdapter(pedidoAdapter);
    }

    // Exemplo de função que retorna pedidos simulados
    private List<Pedido> getPedidosDoUsuario() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("Pedido #001", "Vinho Merlot - 2 unid", "R$ 140,00"));
        pedidos.add(new Pedido("Pedido #002", "Vinho Branco - 1 unid", "R$ 75,00"));
        return pedidos;
    }
}
