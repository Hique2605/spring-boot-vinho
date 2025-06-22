package com.example.lucasappvinho.View.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.endpoint.RepresentanteEndpoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteRepresentanteActivity extends AppCompatActivity {

    private EditText editIdRepresentante;
    private Button btnDeletarRepresentante, btnCancelarRepresentante;
    private RepresentanteEndpoint representanteEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_representante);

        editIdRepresentante = findViewById(R.id.editIdRepresentante);
        btnDeletarRepresentante = findViewById(R.id.btnDeletarRepresentante);
        btnCancelarRepresentante = findViewById(R.id.btnCancelarRepresentante);

        representanteEndpoint = Api.getRepresentanteEndpoint();

        btnDeletarRepresentante.setOnClickListener(v -> deletarRepresentante());
        btnCancelarRepresentante.setOnClickListener(v -> finish());
    }

    private void deletarRepresentante() {
        String idStr = editIdRepresentante.getText().toString().trim();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Informe o ID do representante", Toast.LENGTH_SHORT).show();
            return;
        }

        Long id = Long.parseLong(idStr);
        representanteEndpoint.deletarRepresentante(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DeleteRepresentanteActivity.this, "Representante deletado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DeleteRepresentanteActivity.this, "Erro ao deletar representante", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DeleteRepresentanteActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
