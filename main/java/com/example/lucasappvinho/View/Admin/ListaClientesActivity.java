package com.example.lucasappvinho.View.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.Sessao;
import com.example.lucasappvinho.View.HomeActivity;
import com.example.lucasappvinho.View.TelaVinhosActivity;
import com.example.lucasappvinho.adapter.ClienteAdapter;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaClientesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewClientes;
    private ClienteAdapter adapter;

    private ImageButton btnAdicionarCliente ,btnVoltarOculto, btnVoltar ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_clientes);

        configurarPorTipoUsuario(); //config tipo

        recyclerViewClientes = findViewById(R.id.recyclerViewClientes);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));

        buscarClientes();

        // Botão de voltar para home
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(ListaClientesActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // Botão de voltar para painel
        btnVoltarOculto = findViewById(R.id.btnVoltarOculto);
        btnVoltarOculto.setOnClickListener(view -> {
            Intent intent = new Intent(ListaClientesActivity.this, PainelAdmActivity.class);
            startActivity(intent);
            finish(); // opcional: encerra a TelaVinhosActivity para não acumular na pilha
        });


        // Botão de adicionar cliente
        btnAdicionarCliente = findViewById(R.id.btnAdicionarCliente);

        btnAdicionarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaClientesActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });


    }

    private void buscarClientes() {
        Call<List<User>> call = Api.getUserEndpoint().getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> lista = response.body();
                    adapter = new ClienteAdapter(lista);
                    recyclerViewClientes.setAdapter(adapter);
                } else {
                    Toast.makeText(ListaClientesActivity.this,
                            "Erro ao carregar clientes: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ListaClientesActivity.this,
                        "Falha: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
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
            //nada
        } else {
            btnVoltar.setVisibility(View.VISIBLE);
            btnVoltarOculto.setVisibility(View.GONE);
        }

    }

}
