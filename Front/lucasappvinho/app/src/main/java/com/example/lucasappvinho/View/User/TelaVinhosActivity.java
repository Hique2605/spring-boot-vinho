package com.example.lucasappvinho.View.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.View.HomeActivity;
import com.example.lucasappvinho.adapter.WineAdapter;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Vinho;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaVinhosActivity extends AppCompatActivity {

    private ImageButton btnVoltar;
    private ImageButton btnFiltro;
    private EditText editNomeVinho;
    private RecyclerView recyclerView;
    private List<Vinho> listaVinhos = new ArrayList<>();
    private WineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_vinhos_user);

        // Inicializa os componentes
        btnVoltar = findViewById(R.id.btnVoltar);
        btnFiltro = findViewById(R.id.btnFiltro);
        editNomeVinho = findViewById(R.id.editNomeVinho);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WineAdapter(listaVinhos);
        recyclerView.setAdapter(adapter);

        // Botão de voltar
        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(TelaVinhosActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
        // Carrega todos os vinhos ao abrir a tela
        carregarTodosVinhos();

        // Botão de filtro (buscar por nome)
        btnFiltro.setOnClickListener(view -> {
            String nomeBusca = editNomeVinho.getText().toString().trim();
            if (nomeBusca.isEmpty()) {
                // Se o campo está vazio, recarrega a lista completa
                adapter = new WineAdapter(listaVinhos);
                recyclerView.setAdapter(adapter);
            } else {
                filtrarVinhos(nomeBusca);
            }
        });
    }

    private void carregarTodosVinhos() {
        Api.getVinhoEndpoint().getAllWines().enqueue(new Callback<List<Vinho>>() {
            @Override
            public void onResponse(Call<List<Vinho>> call, Response<List<Vinho>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaVinhos.clear();
                    listaVinhos.addAll(response.body());
                    adapter.notifyDataSetChanged();
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

    private void filtrarVinhos(String nomeFiltro) {
        List<Vinho> filtrados = new ArrayList<>();
        for (Vinho vinho : listaVinhos) {
            if (vinho.getNome().toLowerCase().contains(nomeFiltro.toLowerCase())) {
                filtrados.add(vinho);
            }
        }

        WineAdapter filtroAdapter = new WineAdapter(filtrados);
        recyclerView.setAdapter(filtroAdapter);
    }
}



