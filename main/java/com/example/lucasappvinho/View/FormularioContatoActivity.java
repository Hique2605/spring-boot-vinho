package com.example.lucasappvinho.View;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;

public class FormularioContatoActivity extends AppCompatActivity {

    private ImageButton btnVoltar;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_contato); // nome do seu xml

        btnVoltar = findViewById(R.id.btnVoltar);
        btnEnviar = findViewById(R.id.btnEnviar);

        // Botão voltar fecha essa activity, voltando para a anterior
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Botão enviar também finaliza a activity (pode mudar para enviar dados)
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aqui você pode pegar os dados e validar / enviar...

                // Por enquanto só fecha a tela
                finish();
            }
        });
    }
}
