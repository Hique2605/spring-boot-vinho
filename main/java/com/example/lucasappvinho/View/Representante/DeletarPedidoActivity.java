package com.example.lucasappvinho.View.Representante;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletarPedidoActivity extends AppCompatActivity {

    private EditText editIdPedido;
    private Button btnDeletePedido, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_deletar_pedido);

        editIdPedido = findViewById(R.id.editIdPedido);
        btnDeletePedido = findViewById(R.id.btnDeletarPedido);

        btnDeletePedido.setOnClickListener(v -> {
            String idTexto = editIdPedido.getText().toString().trim();

            if (idTexto.isEmpty()) {
                Toast.makeText(this, "Informe o ID do pedido!", Toast.LENGTH_SHORT).show();
                return;
            }

            Long idPedido;
            try {
                idPedido = Long.parseLong(idTexto);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            deletarPedido(idPedido);
        });


        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> {
            finish(); // Volta para a tela anterior
        });

    }

    private void deletarPedido(Long id) {
        Api.getOrderEndpoint().deletarPedido(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DeletarPedidoActivity.this, "Pedido deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DeletarPedidoActivity.this, "Erro ao deletar pedido. Código: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DeletarPedidoActivity.this, "Erro na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API", "Falha ao deletar pedido", t);
            }
        });
    }
}