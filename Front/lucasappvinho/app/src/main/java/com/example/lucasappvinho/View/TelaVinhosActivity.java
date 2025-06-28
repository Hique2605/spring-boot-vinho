package com.example.lucasappvinho.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.Sessao;
import com.example.lucasappvinho.View.Admin.CadastroVinhoActivity;
import com.example.lucasappvinho.View.Admin.DeleteVinhoActivity;
import com.example.lucasappvinho.View.Admin.PainelAdmActivity;
import com.example.lucasappvinho.View.Admin.UpdateVinhoActivity;
import com.example.lucasappvinho.adapter.WineAdapter;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Vinho;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaVinhosActivity extends AppCompatActivity {

    private ImageButton btnVoltar, btnVoltarOculto, btnAddViho, btnEditViho , btnDeleteViho;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_vinhos);

        configurarPorTipoUsuario();

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            startActivity(new Intent(TelaVinhosActivity.this, HomeActivity.class));
            finish();
        });

        btnVoltarOculto = findViewById(R.id.btnVoltarOculto);
        btnVoltarOculto.setOnClickListener(view -> {
            startActivity(new Intent(TelaVinhosActivity.this, PainelAdmActivity.class));
            finish();
        });

        btnAddViho = findViewById(R.id.btnAddViho);
        btnAddViho.setOnClickListener(v -> startActivity(new Intent(TelaVinhosActivity.this, CadastroVinhoActivity.class)));

        btnEditViho = findViewById(R.id.btnEditViho);
        btnEditViho.setOnClickListener(v -> startActivity(new Intent(TelaVinhosActivity.this, UpdateVinhoActivity.class)));

        btnDeleteViho = findViewById(R.id.btnDeleteViho);
        btnDeleteViho.setOnClickListener(v -> startActivity(new Intent(TelaVinhosActivity.this, DeleteVinhoActivity.class)));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarVinhos(); // Atualiza a lista toda vez que voltar para essa tela
    }

    private void carregarVinhos() {
        Api.getVinhoEndpoint().getAllWines().enqueue(new Callback<List<Vinho>>() {
            @Override
            public void onResponse(Call<List<Vinho>> call, Response<List<Vinho>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Vinho> vinhos = response.body();
                    recyclerView.setAdapter(new WineAdapter(vinhos));
                } else {
                    Toast.makeText(TelaVinhosActivity.this, "Erro ao carregar vinhos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vinho>> call, Throwable t) {
                Toast.makeText(TelaVinhosActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarPorTipoUsuario() {
        boolean isAdmin = Sessao.tipoUsuario.equals("admin");
        boolean isUser = Sessao.tipoUsuario.equals("user");

        btnAddViho = findViewById(R.id.btnAddViho);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltarOculto = findViewById(R.id.btnVoltarOculto);
        btnEditViho = findViewById(R.id.btnEditViho);
        btnDeleteViho = findViewById(R.id.btnDeleteViho);

        if (isAdmin) {
            btnAddViho.setVisibility(View.VISIBLE);
            btnVoltar.setVisibility(View.GONE);
            btnVoltarOculto.setVisibility(View.VISIBLE);
        } else {
            btnAddViho.setVisibility(View.GONE);
            btnEditViho.setVisibility(View.GONE);
            btnDeleteViho.setVisibility(View.GONE);
        }
    }
}

