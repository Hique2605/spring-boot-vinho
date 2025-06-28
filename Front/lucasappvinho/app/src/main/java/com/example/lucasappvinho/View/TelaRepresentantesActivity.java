package com.example.lucasappvinho.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.Sessao;
import com.example.lucasappvinho.View.Admin.CadastroRepresentanteActivity;
import com.example.lucasappvinho.View.Admin.DeleteRepresentanteActivity;
import com.example.lucasappvinho.View.Admin.PainelAdmActivity;
import com.example.lucasappvinho.View.Admin.UpdateRepresentanteActivity;
import com.example.lucasappvinho.adapter.RepresentanteAdapter;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Representante;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaRepresentantesActivity extends AppCompatActivity {

    private ImageButton btnVoltar, btnVoltarOculto, btnAddRepresentante, btnEditRepresentante, btnDeleteRepresentante;
    private RecyclerView recyclerView;
    private RepresentanteAdapter representanteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_representantes);

        configurarPorTipoUsuario();

        // Botões
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltarOculto = findViewById(R.id.btnVoltarOculto);
        btnAddRepresentante = findViewById(R.id.btnAddRep);
        btnEditRepresentante = findViewById(R.id.btnEditRep);
        btnDeleteRepresentante = findViewById(R.id.btnDeleteRep);

        btnVoltar.setOnClickListener(v -> {
            startActivity(new Intent(TelaRepresentantesActivity.this, HomeActivity.class));
            finish();
        });

        btnVoltarOculto.setOnClickListener(v -> {
            startActivity(new Intent(TelaRepresentantesActivity.this, PainelAdmActivity.class));
            finish();
        });

        btnAddRepresentante.setOnClickListener(v -> startActivity(new Intent(this, CadastroRepresentanteActivity.class)));
        btnEditRepresentante.setOnClickListener(v -> startActivity(new Intent(this, UpdateRepresentanteActivity.class)));
        btnDeleteRepresentante.setOnClickListener(v -> startActivity(new Intent(this, DeleteRepresentanteActivity.class)));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarRepresentantes(); // Atualiza lista toda vez que volta à tela
    }

    private void carregarRepresentantes() {
        Api.getRepresentanteEndpoint().getAllRepresentantes().enqueue(new Callback<List<Representante>>() {
            @Override
            public void onResponse(Call<List<Representante>> call, Response<List<Representante>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Representante> representantes = response.body();

                    if (representanteAdapter == null) {
                        representanteAdapter = new RepresentanteAdapter(representantes);
                        recyclerView.setAdapter(representanteAdapter);
                    } else {
                        representanteAdapter.atualizarLista(representantes); // precisa criar no Adapter
                    }

                } else {
                    Toast.makeText(TelaRepresentantesActivity.this, "Erro ao carregar representantes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Representante>> call, Throwable t) {
                Toast.makeText(TelaRepresentantesActivity.this, "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarPorTipoUsuario() {
        boolean isAdmin = "admin".equals(Sessao.tipoUsuario);
        boolean isUser = "user".equals(Sessao.tipoUsuario);

        btnAddRepresentante = findViewById(R.id.btnAddRep);
        btnEditRepresentante = findViewById(R.id.btnEditRep);
        btnDeleteRepresentante = findViewById(R.id.btnDeleteRep);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltarOculto = findViewById(R.id.btnVoltarOculto);

        if (isAdmin) {
            btnAddRepresentante.setVisibility(View.VISIBLE);
            btnEditRepresentante.setVisibility(View.VISIBLE);
            btnDeleteRepresentante.setVisibility(View.VISIBLE);
            btnVoltar.setVisibility(View.GONE);
            btnVoltarOculto.setVisibility(View.VISIBLE);
        } else {
            btnAddRepresentante.setVisibility(View.GONE);
            btnEditRepresentante.setVisibility(View.GONE);
            btnDeleteRepresentante.setVisibility(View.GONE);
            btnVoltar.setVisibility(View.VISIBLE);
            btnVoltarOculto.setVisibility(View.GONE);
        }
    }
}
