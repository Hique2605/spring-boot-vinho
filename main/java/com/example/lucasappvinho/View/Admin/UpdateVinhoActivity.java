package com.example.lucasappvinho.View.Admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.endpoint.VinhoEndpoint;
import com.example.lucasappvinho.api.model.Vinho;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateVinhoActivity extends AppCompatActivity {

    private EditText editIdBusca, editNome, editSafra, editTipo, editUva, editTeorAlcoolico,
            editVolume, editNotasDegustacao, editHarmonizacao, editPrecoUnitario,
            editimgUrl, editQuantidade;
    private Switch switchEmEstoque;

    private Button btnAtualizar, btnCancelar, btnBuscar;

    private Long vinhoId;
    private VinhoEndpoint vinhoEndpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_vinho);

        // Inicializa os componentes
        editIdBusca = findViewById(R.id.editIdBusca);
        editNome = findViewById(R.id.editNome);
        editSafra = findViewById(R.id.editSafra);
        editTipo = findViewById(R.id.editTipo);
        editUva = findViewById(R.id.editUva);
        editTeorAlcoolico = findViewById(R.id.editTeorAlcoolico);
        editVolume = findViewById(R.id.editVolume);
        editNotasDegustacao = findViewById(R.id.editNotasDegustacao);
        editHarmonizacao = findViewById(R.id.editHarmonizacao);
        editPrecoUnitario = findViewById(R.id.editPrecoUnitario);
        editimgUrl = findViewById(R.id.editimgUrl);
        editQuantidade = findViewById(R.id.editQuantidade);
        switchEmEstoque = findViewById(R.id.switchEmEstoque);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnBuscar = findViewById(R.id.btnBuscar);

        vinhoEndpoint = Api.getVinhoEndpoint();

        btnBuscar.setOnClickListener(v -> buscarVinhoPorId());
        btnAtualizar.setOnClickListener(v -> atualizarVinho());
        btnCancelar.setOnClickListener(v -> finish());

    }

    private void buscarVinhoPorId() {
        String idStr = editIdBusca.getText().toString();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Digite o ID do vinho", Toast.LENGTH_SHORT).show();
            return;
        }

        vinhoId = Long.parseLong(idStr);
        vinhoEndpoint.findById(vinhoId).enqueue(new Callback<Vinho>() {
            @Override
            public void onResponse(Call<Vinho> call, Response<Vinho> response) {
                if (response.isSuccessful() && response.body() != null) {
                    preencherCampos(response.body());
                    Toast.makeText(UpdateVinhoActivity.this, "Vinho carregado!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateVinhoActivity.this, "Vinho não encontrado!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Vinho> call, Throwable t) {
                Toast.makeText(UpdateVinhoActivity.this, "Erro ao buscar vinho!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void preencherCampos(Vinho vinho) {
        editNome.setText(vinho.getNome());
        editSafra.setText(String.valueOf(vinho.getSafra()));
        editTipo.setText(vinho.getTipo());
        editUva.setText(vinho.getUva());
        editTeorAlcoolico.setText(vinho.getTeorAlcoolico());
        editVolume.setText(vinho.getVolume());
        editNotasDegustacao.setText(vinho.getNotasDegustacao());
        editHarmonizacao.setText(vinho.getHarmonizacao());
        editPrecoUnitario.setText(String.valueOf(vinho.getPrecoUnitario()));
        editimgUrl.setText(vinho.getImgUrl());
        editQuantidade.setText(String.valueOf(vinho.getQuantidade()));
        switchEmEstoque.setChecked(Boolean.TRUE.equals(vinho.getEmEstoque()));
    }

    private void atualizarVinho() {
        if (vinhoId == null) {
            Toast.makeText(this, "Busque um vinho primeiro", Toast.LENGTH_SHORT).show();
            return;
        }

        Vinho vinho = new Vinho();
        vinho.setNome(editNome.getText().toString());
        vinho.setSafra(editSafra.getText().toString());
        vinho.setTipo(editTipo.getText().toString());
        vinho.setUva(editUva.getText().toString());
        vinho.setTeorAlcoolico(editTeorAlcoolico.getText().toString());
        vinho.setVolume(editVolume.getText().toString());
        vinho.setNotasDegustacao(editNotasDegustacao.getText().toString());
        vinho.setHarmonizacao(editHarmonizacao.getText().toString());
        vinho.setPrecoUnitario(Double.parseDouble(editPrecoUnitario.getText().toString()));
        vinho.setImgUrl(editimgUrl.getText().toString());
        vinho.setQuantidade(Double.parseDouble(editQuantidade.getText().toString()));
        vinho.setEmEstoque(switchEmEstoque.isChecked());

        vinhoEndpoint.updateVinho(vinhoId, vinho).enqueue(new Callback<Vinho>() {
            @Override
            public void onResponse(Call<Vinho> call, Response<Vinho> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateVinhoActivity.this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateVinhoActivity.this, "Erro ao atualizar vinho", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Vinho> call, Throwable t) {
                Toast.makeText(UpdateVinhoActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
