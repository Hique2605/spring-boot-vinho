package com.example.lucasappvinho.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.View.HomeActivity;
import com.example.lucasappvinho.View.PedidosActivity;
import com.example.lucasappvinho.View.TelaRepresentantesActivity;
import com.example.lucasappvinho.View.TelaVinhosActivity;

public class PainelAdmActivity extends AppCompatActivity {

    private ImageButton btnVoltar;
    private LinearLayout btnVinhos;
    private LinearLayout btnClientes;
    private LinearLayout btnRepresentantes;
    private LinearLayout btnPedidos;
    private LinearLayout btnRelatorios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_admin);

        // Botão de voltar para HomeActivity
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(PainelAdmActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // opcional: encerra a PainelAdmActivity para não acumular na pilha
        });

        // Inicializar os botões (seções do painel)
        btnVinhos = findViewById(R.id.btnVinhos);
        btnClientes = findViewById(R.id.btnClientes);
        btnRepresentantes = findViewById(R.id.btnRepresentantes);
        btnPedidos = findViewById(R.id.btnPedidos);
        btnRelatorios = findViewById(R.id.btnRelatorios);

        // Clique para cada item
        btnVinhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelAdmActivity.this, TelaVinhosActivity.class);
                startActivity(intent);
            }
        });

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelAdmActivity.this, ListaClientesActivity.class);
                startActivity(intent);
            }
        });

        btnRepresentantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelAdmActivity.this, TelaRepresentantesActivity.class);
                startActivity(intent);
            }
        });

        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelAdmActivity.this, PedidosActivity.class);
                startActivity(intent);
            }
        });
/*
        btnRelatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelAdmActivity.this, RelatoriosActivity.class);
                startActivity(intent);
            }
        })
        */
    }
}