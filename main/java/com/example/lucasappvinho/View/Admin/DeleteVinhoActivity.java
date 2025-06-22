package com.example.lucasappvinho.View.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.endpoint.VinhoEndpoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteVinhoActivity extends AppCompatActivity {

    private EditText editIdBusca;
    private Button btnDeletar, btnCancelar;
    private VinhoEndpoint vinhoEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_vinho);

        editIdBusca = findViewById(R.id.editIdBusca);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnCancelar = findViewById(R.id.btnCancelar);

        vinhoEndpoint = Api.getVinhoEndpoint();

        btnDeletar.setOnClickListener(v -> deletarVinho());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void deletarVinho() {
        String idStr = editIdBusca.getText().toString().trim();

        if (idStr.isEmpty()) {
            Toast.makeText(this, "Digite o ID do vinho", Toast.LENGTH_SHORT).show();
            return;
        }

        Long vinhoId = Long.parseLong(idStr);

        Call<Void> call = vinhoEndpoint.deletarVinho(vinhoId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DeleteVinhoActivity.this, "Vinho deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish(); // Fecha a activity
                } else {
                    Toast.makeText(DeleteVinhoActivity.this, "Erro ao deletar vinho. Verifique o ID.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DeleteVinhoActivity.this, "Erro de conexão.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}