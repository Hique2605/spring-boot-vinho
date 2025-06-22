package com.example.lucasappvinho.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.Sessao;
import com.example.lucasappvinho.R;
import com.example.lucasappvinho.View.Admin.ListaClientesActivity;
import com.example.lucasappvinho.View.Admin.PainelAdmActivity;
import com.example.lucasappvinho.View.User.UserPedidosActivity;
import com.example.lucasappvinho.adapter.WineAdapter;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Vinho;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton menuButton;
    private ImageButton menuWineButton;
    private ImageButton menuPainelButton;
    private ImageButton PedidoButton;
    private NavigationView navigationView;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        menuButton = findViewById(R.id.menuButton);
        menuWineButton = findViewById(R.id.menuWineButton);
        menuPainelButton = findViewById(R.id.menuPainelButton);
        navigationView = findViewById(R.id.navigationView);
        recyclerView = findViewById(R.id.recyclerView);
        PedidoButton = findViewById(R.id.menuPedido);


        configurarMenuPorTipoUsuario();

        navigationView.setBackgroundColor(getResources().getColor(R.color.creme));

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        menuWineButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TelaVinhosActivity.class);
            startActivity(intent);
        });

        menuPainelButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PainelAdmActivity.class);
            startActivity(intent);
        });

        PedidoButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PedidosActivity.class);
            startActivity(intent);
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Página inicial
            } else if (id == R.id.nav_vinhos) {
                startActivity(new Intent(this, TelaVinhosActivity.class));
            } else if (id == R.id.nav_pedidos) {
                startActivity(new Intent(this, PedidosActivity.class));
            } else if (id == R.id.nav_Clientes) {
                startActivity(new Intent(this, ListaClientesActivity.class));
            }else if (id == R.id.nav_meus_pedidos) {
                    startActivity(new Intent(this, UserPedidosActivity.class));
            } else if (id == R.id.nav_form) {
                startActivity(new Intent(this, FormularioContatoActivity.class));
            } else if (id == R.id.nav_historia) {
                startActivity(new Intent(this, HistoriaActivity.class));
            } else if (id == R.id.nav_adm) {    //nav_adm
                startActivity(new Intent(this, PainelAdmActivity.class));
            } else if (id == R.id.nav_sair) {
                finishAffinity(); // Fecha todas as activities da pilha
                System.exit(0);   // Encerra o processo do app (opcional)
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Requisição Retrofit para obter vinhos
        Api.getVinhoEndpoint().getAllWines().enqueue(new Callback<List<Vinho>>() {
            @Override
            public void onResponse(Call<List<Vinho>> call, Response<List<Vinho>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Vinho> vinhos = response.body();
                    recyclerView.setAdapter(new WineAdapter(vinhos));
                } else {
                    Toast.makeText(HomeActivity.this, "Erro ao carregar vinhos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vinho>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Falha na conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarMenuPorTipoUsuario() {
        boolean isAdmin = Sessao.tipoUsuario.equals("admin");
        boolean isUser = Sessao.tipoUsuario.equals("user");

        // Acessar menu da NavigationView
        Menu menu = navigationView.getMenu();
        MenuItem menuAdm = menu.findItem(R.id.nav_adm);
        MenuItem menupedidos = menu.findItem(R.id.nav_pedidos);
        MenuItem menumeuspedidos = menu.findItem(R.id.nav_meus_pedidos);
        MenuItem menuWine = menu.findItem(R.id.nav_vinhos);
        MenuItem menuClientes = menu.findItem(R.id.nav_Clientes);

        if (isAdmin) {
            menuPainelButton.setVisibility(View.VISIBLE);
            menuWineButton.setVisibility(View.GONE);

            menupedidos.setVisible(false);// Oculta item "PEDIDOS"
            menumeuspedidos.setVisible(false); // Oculta item "MEUS PEDIDOS"
            menuAdm.setVisible(true); // Mostra item "Painel Administrativo"
            menuWine.setVisible(false);// Oculta item "vinhos"  em menu
            menuClientes.setVisible(false);// Oculta item "clientes"

        } else if (isUser) {
            menuPainelButton.setVisibility(View.GONE);
            menuWineButton.setVisibility(View.VISIBLE);

            menuAdm.setVisible(false); // Oculta item "Painel Administrativo"
            menupedidos.setVisible(false);// Oculta item "PEDIDOS"
            menuClientes.setVisible(false);// Oculta item "clientes"
        } else {
            menuPainelButton.setVisibility(View.GONE);
            menuWineButton.setVisibility(View.GONE);
            PedidoButton.setVisibility(View.VISIBLE);

            menumeuspedidos.setVisible(false); // Oculta item "MEUS PEDIDOS"
            menuAdm.setVisible(false); // Oculta item "Painel Administrativo"
        }
    }
}
