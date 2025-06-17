package com.example.lucasappvinho.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;

public class CadastroVinhoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_vinho);

        Button btnVoltar = findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroVinhoActivity.this, TelaVinhosAdminActivity.class);
                startActivity(intent);
                finish(); // Encerra esta activity para não voltar com o botão "voltar"
            }
        });
    }
}
