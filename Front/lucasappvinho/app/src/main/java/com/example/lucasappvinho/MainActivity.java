package com.example.lucasappvinho;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.Sessao;
import com.example.lucasappvinho.View.HomeActivity;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Admin;
import com.example.lucasappvinho.api.model.Representante;
import com.example.lucasappvinho.api.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editPassword;

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

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(v -> tentarLogin());
    }

    private void tentarLogin() {
        String email = editEmail.getText().toString().trim();
        String senha = editPassword.getText().toString();

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        tentarLoginComoAdmin(email, senha);
    }

    private void tentarLoginComoAdmin(String email, String senha) {
        Api.findByEmailAdmin(email, new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Admin admin = response.body();
                    if (senha.equals(admin.getPassword())) {
                        Sessao.tipoUsuario = "admin";
                        Sessao.idUsuarioLogado = admin.getId();
                        Toast.makeText(MainActivity.this, "Login como ADMIN", Toast.LENGTH_SHORT).show();
                        abrirHome();
                    } else {
                        Toast.makeText(MainActivity.this, "Senha incorreta (Admin)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    tentarLoginComoRepresentante(email, senha);
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                tentarLoginComoRepresentante(email, senha);
            }
        });
    }

    private void tentarLoginComoRepresentante(String email, String senha) {
        Api.findByEmailRepresentante(email, new Callback<Representante>() {
            @Override
            public void onResponse(Call<Representante> call, Response<Representante> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Representante representante = response.body();
                    if (senha.equals(representante.getPassword())) {
                        Sessao.tipoUsuario = "representante";
                        Sessao.idUsuarioLogado = representante.getId();
                        Toast.makeText(MainActivity.this, "Login como REPRESENTANTE", Toast.LENGTH_SHORT).show();
                        abrirHome();
                    } else {
                        Toast.makeText(MainActivity.this, "Senha incorreta (Representante)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    tentarLoginComoUser(email, senha);
                }
            }

            @Override
            public void onFailure(Call<Representante> call, Throwable t) {
                tentarLoginComoUser(email, senha);
            }
        });
    }

    private void tentarLoginComoUser(String email, String senha) {
        Api.findByEmailUser(email, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    if (senha.equals(user.getPassword())) {
                        Sessao.tipoUsuario = "user";
                        Sessao.idUsuarioLogado = user.getId();
                        Toast.makeText(MainActivity.this, "Login como USER", Toast.LENGTH_SHORT).show();
                        abrirHome();
                    } else {
                        Toast.makeText(MainActivity.this, "Senha incorreta (User)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "E-mail não cadastrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void abrirHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
