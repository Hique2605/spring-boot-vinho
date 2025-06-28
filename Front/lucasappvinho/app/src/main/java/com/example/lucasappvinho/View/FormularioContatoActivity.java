package com.example.lucasappvinho.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;

public class FormularioContatoActivity extends AppCompatActivity {

    private ImageButton btnVoltar;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_contato); // nome do seu XML

        btnVoltar = findViewById(R.id.btnVoltar);
        btnEnviar = findViewById(R.id.btnEnviar);

        // Botão voltar fecha essa activity, voltando para a anterior
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Botão enviar mostra log e toast, e finaliza a activity
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mostra Toast na tela
                Toast.makeText(FormularioContatoActivity.this, "Mensagem enviada com sucesso!", Toast.LENGTH_SHORT).show();

                // Log no Logcat
                Log.d("FormularioContato", "Mensagem enviada com sucesso!");

                // Fecha a Activity
                finish();
            }
        });
    }
}