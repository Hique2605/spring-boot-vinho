package com.example.lucasappvinho.View.Admin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.endpoint.RepresentanteEndpoint;
import com.example.lucasappvinho.api.model.Representante;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateRepresentanteActivity extends AppCompatActivity {

    private EditText editId, editNome, editContato, editTelefone;
    private Button btnBuscar, btnAtualizar, btnCancelar;

    private EditText editCpf, editSenha, editMeta;
    private RepresentanteEndpoint representanteEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_representante);

        editId = findViewById(R.id.editIdRepresentante);
        editNome = findViewById(R.id.editNome);
        editContato = findViewById(R.id.editContato);
        editTelefone = findViewById(R.id.editTelefone);

        btnBuscar = findViewById(R.id.btnBuscar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnCancelar = findViewById(R.id.btnCancelar);

        editCpf = findViewById(R.id.editCpf);
        editSenha = findViewById(R.id.editSenha);
        editMeta = findViewById(R.id.editMeta);

        representanteEndpoint = Api.getRepresentanteEndpoint();

        btnBuscar.setOnClickListener(v -> buscarRepresentante());
        btnAtualizar.setOnClickListener(v -> atualizarRepresentante());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void buscarRepresentante() {
        String idStr = editId.getText().toString().trim();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Informe o ID do representante", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Long id = Long.parseLong(idStr);
            Call<Representante> call = representanteEndpoint.getById(id);

            call.enqueue(new Callback<Representante>() {
                @Override
                public void onResponse(Call<Representante> call, Response<Representante> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        preencherCampos(response.body());
                    } else {
                        Log.e("API", "Erro buscarRepresentante - code: " + response.code());
                        Toast.makeText(UpdateRepresentanteActivity.this, "Representante não encontrado", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Representante> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(UpdateRepresentanteActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private void preencherCampos(Representante representante) {
        editNome.setText(representante.getNome());
        editCpf.setText(representante.getCpf());
        editContato.setText(representante.getEmail());
        editTelefone.setText(representante.getTelefone());
        editSenha.setText(representante.getPassword());
        editMeta.setText(String.valueOf(representante.getMeta()));
    }

    private void atualizarRepresentante() {
        String idStr = editId.getText().toString().trim();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Informe o ID para atualizar", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Long id = Long.parseLong(idStr);

            Representante representante = new Representante();
            representante.setNome(editNome.getText().toString().trim());
            representante.setCpf(editCpf.getText().toString().trim());
            representante.setEmail(editContato.getText().toString().trim());
            representante.setTelefone(editTelefone.getText().toString().trim());
            representante.setPassword(editSenha.getText().toString().trim());
            representante.setMeta(Double.parseDouble(editMeta.getText().toString().trim()));


            Call<Representante> call = representanteEndpoint.update(id, representante);
            call.enqueue(new Callback<Representante>() {
                @Override
                public void onResponse(Call<Representante> call, Response<Representante> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(UpdateRepresentanteActivity.this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Log.e("API", "Erro atualizarRepresentante - code: " + response.code());
                        Toast.makeText(UpdateRepresentanteActivity.this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Representante> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(UpdateRepresentanteActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show();
        }
    }
}