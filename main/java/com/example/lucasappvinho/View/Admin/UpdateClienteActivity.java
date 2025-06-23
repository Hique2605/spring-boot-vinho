package com.example.lucasappvinho.View.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.endpoint.UserEndpoint;
import com.example.lucasappvinho.api.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateClienteActivity extends AppCompatActivity {

    private EditText editIdCliente, editTextNome, editTextSenha, editTextEmail, editTextTelefone, editTextEndereco;
    private Switch switchAtivo;
    private Button btnBuscarCliente, btnGravar, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_cliente);



        editIdCliente    = findViewById(R.id.editIdCliente);
        editTextNome     = findViewById(R.id.editTextNome);
        editTextSenha    = findViewById(R.id.editTextSenha);
        editTextEmail    = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        switchAtivo      = findViewById(R.id.switchAtivo);

        btnBuscarCliente = findViewById(R.id.btnBuscarCliente);
        btnGravar        = findViewById(R.id.btnGravar);
        btnVoltar        = findViewById(R.id.btnVoltar);

        btnBuscarCliente.setOnClickListener(v -> buscarClientePorId());
        btnGravar.setOnClickListener(v -> atualizarCliente());
        btnVoltar.setOnClickListener(v -> finish());
    }

    private void buscarClientePorId() {
        String idTexto = editIdCliente.getText().toString().trim();
        if (idTexto.isEmpty()) {
            Toast.makeText(this, "Informe o ID do cliente", Toast.LENGTH_SHORT).show();
            return;
        }

        long id = Long.parseLong(idTexto);

        Api.getUserEndpoint().findById(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    preencherCampos(response.body());
                } else {
                    Toast.makeText(UpdateClienteActivity.this, "Cliente não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UpdateClienteActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void preencherCampos(User user) {
        editTextNome.setText(user.getName());
        editTextSenha.setText(user.getPassword());
        editTextEmail.setText(user.getEmail());
        editTextTelefone.setText(user.getPhone());
        editTextEndereco.setText(user.getEndereco());
        //switchAtivo.setChecked(user.isAtivo());
    }

    private void atualizarCliente() {
        String idTexto = editIdCliente.getText().toString().trim();
        if (idTexto.isEmpty()) {
            Toast.makeText(this, "ID obrigatório para atualizar", Toast.LENGTH_SHORT).show();
            return;
        }

        Long id = Long.parseLong(idTexto);

        User user = new User();
        user.setId(id);
        user.setName(editTextNome.getText().toString());
        user.setPassword(editTextSenha.getText().toString());
        user.setEmail(editTextEmail.getText().toString());
        user.setPhone(editTextTelefone.getText().toString());
        user.setEndereco(editTextEndereco.getText().toString());

        //user.setAtivo(switchAtivo.isChecked());

        Api.getUserEndpoint().update(id, user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateClienteActivity.this, "Cliente atualizado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateClienteActivity.this, "Erro ao atualizar: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UpdateClienteActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
