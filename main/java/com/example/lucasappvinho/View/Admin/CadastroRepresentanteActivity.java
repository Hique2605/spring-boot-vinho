package com.example.lucasappvinho.View.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Enums.UserType;
import com.example.lucasappvinho.api.model.Representante;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroRepresentanteActivity extends AppCompatActivity {

    private EditText editTextNome, editTextSenha, editTextTelefone,
            editTextEmail, editTextMeta, editTextCPF;
    private Button btnGravar, btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_representante);

        inicializarComponentes();

        btnGravar.setOnClickListener(v -> cadastrarRepresentante());
        btnVoltar.setOnClickListener(v -> finish());
    }

    private void inicializarComponentes() {
        editTextNome = findViewById(R.id.editTextNome);
        editTextCPF = findViewById(R.id.editTextCPF);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextSenha = findViewById(R.id.editTextSenha);
        editTextMeta = findViewById(R.id.editTextMeta);


        btnGravar = findViewById(R.id.btnGravar);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    private void cadastrarRepresentante() {
        String nome = editTextNome.getText().toString().trim();
        String cpf = editTextCPF.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String telefone = editTextTelefone.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();
        String metaStr = editTextMeta.getText().toString().trim();


        if (nome.isEmpty() || senha.isEmpty() || telefone.isEmpty()
                || email.isEmpty() || metaStr.isEmpty() || cpf.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double meta;
        try {
            meta = Double.parseDouble(metaStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Meta inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        Representante representante = new Representante(
                null,
                UserType.REPRESENTANTE,  // Aqui passa o enum UserType
                nome,
                cpf,
                email,
                telefone,
                senha,
                meta
        );


        Call<Representante> call = Api.getRepresentanteEndpoint().insert(representante);
        call.enqueue(new Callback<Representante>() {
            @Override
            public void onResponse(Call<Representante> call, Response<Representante> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroRepresentanteActivity.this,
                            "Representante cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CadastroRepresentanteActivity.this,
                            "Erro ao cadastrar: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Representante> call, Throwable t) {
                Toast.makeText(CadastroRepresentanteActivity.this,
                        "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}