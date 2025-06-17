package com.example.lucasappvinho.View;

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

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.Sessao;
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
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentarLoginComoAdmin();
            }
        });
    }

    private void tentarLoginComoAdmin() {
        Api.findByEmailAdmin(editEmail.getText().toString(), new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Admin admin = response.body();
                    if (admin.getPassword() != null && admin.getPassword().equals(editPassword.getText().toString())) {
                        Sessao.tipoUsuario = "admin";
                        Toast.makeText(MainActivity.this, "Login como ADMIN", Toast.LENGTH_LONG).show();
                        abrirHome();
                    } else {
                        Toast.makeText(MainActivity.this, "Senha incorreta (Admin)", Toast.LENGTH_LONG).show();
                    }
                } else {
                    tentarLoginComoRepresentante();
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                tentarLoginComoRepresentante();
            }
        });
    }

    private void tentarLoginComoRepresentante() {
        Api.findByEmailRepresentante(editEmail.getText().toString(), new Callback<Representante>() {
            @Override
            public void onResponse(Call<Representante> call, Response<Representante> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Representante representante = response.body();
                    if (representante.getPassword() != null && representante.getPassword().equals(editPassword.getText().toString())) {
                        Sessao.tipoUsuario = "representante";
                        Toast.makeText(MainActivity.this, "Login como REPRESENTANTE", Toast.LENGTH_LONG).show();
                        abrirHome();
                    } else {
                        Toast.makeText(MainActivity.this, "Senha incorreta (Representante)", Toast.LENGTH_LONG).show();
                    }
                } else {
                    tentarLoginComoUser();
                }
            }

            @Override
            public void onFailure(Call<Representante> call, Throwable t) {
                tentarLoginComoUser();
            }
        });
    }

    private void tentarLoginComoUser() {
        Api.findByEmailUser(editEmail.getText().toString(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    if (user.getPassword() != null && user.getPassword().equals(editPassword.getText().toString())) {
                        Sessao.tipoUsuario = "user";
                        Toast.makeText(MainActivity.this, "Login como USER", Toast.LENGTH_LONG).show();
                        abrirHome();
                    } else {
                        Toast.makeText(MainActivity.this, "Senha incorreta (User)", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "E-mail não cadastrado em nenhum tipo de conta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro de conexão ao tentar login", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void abrirHome() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
