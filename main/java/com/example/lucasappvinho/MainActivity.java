package com.example.lucasappvinho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPassword = findViewById(R.id.editPassword);

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Api.findByEmail(editEmail.getText().toString(), new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response != null && response.isSuccessful()) {
                            User userResponse = response.body();

                            if (userResponse != null) {
                                if (userResponse.getPassword().equals(editPassword.getText().toString())) {
                                    Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(MainActivity.this, "Senha incorreta!", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Erro ao processar os dados!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            // Se a resposta não for bem-sucedida (ex.: 404 ou outro erro)
                            Toast.makeText(MainActivity.this, "E-mail não cadastrado!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // Mensagem de erro
                        Toast.makeText(MainActivity.this, "FALHOU 1111 !!!!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}