package com.example.lucasappvinho.View.Admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Vinho;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroVinhoActivity extends AppCompatActivity {

    private EditText editNome, editSafra, editTipo, editUva, editTeorAlcoolico, editVolume, editNotasDegustacao, editHarmonizacao, editPrecoUnitario,editImgUrl,editQuantidade;
    private Switch switchEstoque;
    private Button btnGravar, btnVoltar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_vinho);  // xml

        // Referenciando os campos
        editNome = findViewById(R.id.editNome);
        editSafra = findViewById(R.id.editSafra);
        editTipo = findViewById(R.id.editTipo);
        editUva = findViewById(R.id.editUva);
        editTeorAlcoolico = findViewById(R.id.editTeorAlcoolico);
        editVolume = findViewById(R.id.editVolume);
        editNotasDegustacao = findViewById(R.id.editNotasDegustacao);
        editHarmonizacao = findViewById(R.id.editHarmonizacao);
        editPrecoUnitario = findViewById(R.id.editPrecoUnitario);
        editImgUrl = findViewById(R.id.editImgUrl);
        editQuantidade = findViewById(R.id.editQuantidade);
        switchEstoque = findViewById(R.id.switchEmEstoque);

        // Referenciando os botões
        btnGravar = findViewById(R.id.btnGravar);
        btnVoltar = findViewById(R.id.btnVoltar);

        // Clique no botão GRAVAR
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarVinho();
            }
        });

        // Clique no botão VOLTAR
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Fecha a tela
            }
        });
    }

    private void salvarVinho() {
        // Monta o objeto Vinho
        Vinho vinho = new Vinho();
        vinho.setNome(editNome.getText().toString());
        vinho.setSafra(editSafra.getText().toString());
        vinho.setTipo(editTipo.getText().toString());
        vinho.setUva(editUva.getText().toString());
        vinho.setTeorAlcoolico(editTeorAlcoolico.getText().toString());
        vinho.setVolume(editVolume.getText().toString());
        vinho.setNotasDegustacao(editNotasDegustacao.getText().toString());
        vinho.setHarmonizacao(editHarmonizacao.getText().toString());
        vinho.setPrecoUnitario(Double.valueOf(editPrecoUnitario.getText().toString()));
        vinho.setImgUrl(editImgUrl.getText().toString());
        vinho.setQuantidade(Double.valueOf(editQuantidade.getText().toString()));
        vinho.setEmEstoque(switchEstoque.isChecked());




        // Chamada Retrofit
        Api.getVinhoEndpoint().salvarVinho(vinho).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroVinhoActivity.this, "Vinho cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();  // Fecha a tela depois de salvar

                } else {
                    Toast.makeText(CadastroVinhoActivity.this, "Erro ao salvar: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CadastroVinhoActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
