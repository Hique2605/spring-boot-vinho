package com.example.lucasappvinho.View.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.endpoint.UserEndpoint;
import com.example.lucasappvinho.api.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextTelefone, editTextEndereco, editTextSenha;
    private Button btnGravar, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_clientes);

        editTextNome     = findViewById(R.id.editTextNome);
        editTextEmail    = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        editTextSenha    = findViewById(R.id.editTextSenha);

        btnGravar = findViewById(R.id.btnGravar);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnGravar.setOnClickListener(v -> cadastrarCliente());
        btnVoltar.setOnClickListener(v -> finish());
    }

    private void cadastrarCliente() {
        String nome     = editTextNome    .getText().toString().trim();
        String email    = editTextEmail   .getText().toString().trim();
        String telefone = editTextTelefone.getText().toString().trim();
        String endereco = editTextEndereco.getText().toString().trim();
        String senha    = editTextSenha   .getText().toString().trim();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Monta o objeto User (tipo sempre "USER")
        User user = new User(nome, email, telefone, endereco, senha);

        // Chama o endpoint
        UserEndpoint endpoint = Api.getUserEndpoint();
        Call<User> call = endpoint.insert(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroClienteActivity.this,
                            "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CadastroClienteActivity.this,
                            "Erro ao cadastrar: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(CadastroClienteActivity.this,
                        "Falha na conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
