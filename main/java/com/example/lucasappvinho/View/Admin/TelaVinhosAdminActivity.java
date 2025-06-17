package com.example.lucasappvinho.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.adapter.WineAdapter;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Vinho;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaVinhosAdminActivity extends AppCompatActivity {

    private ImageButton btnVoltar;

     private ImageButton btnAddViho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_vinhos_admin);  // carrega o layout tela_vinhos.xml

        // Botão de voltar para painel
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaVinhosAdminActivity.this, PainelAdmActivity.class);
            startActivity(intent);
            finish(); // opcional: encerra a TelaVinhosActivity para não acumular na pilha
        });


        // Botão de adicionar vinho
        btnAddViho = findViewById(R.id.btnAddViho);

        btnAddViho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaVinhosAdminActivity.this, CadastroVinhoActivity.class);
                startActivity(intent);
            }
        });


        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Chamada da API para obter a lista de vinhos
        Api.getVinhoEndpoint().getAllWines().enqueue(new Callback<List<Vinho>>() {
            @Override
            public void onResponse(Call<List<Vinho>> call, Response<List<Vinho>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Vinho> vinhos = response.body();
                    recyclerView.setAdapter(new WineAdapter(vinhos));
                } else {
                    Toast.makeText(TelaVinhosAdminActivity.this, "Erro ao carregar vinhos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vinho>> call, Throwable t) {
                Toast.makeText(TelaVinhosAdminActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
