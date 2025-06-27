package com.example.lucasappvinho.View.Representante;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucasappvinho.R;
import com.example.lucasappvinho.Sessao;
import com.example.lucasappvinho.View.PedidosActivity;
import com.example.lucasappvinho.adapter.OrderItemAdapter;
import com.example.lucasappvinho.api.Api;
import com.example.lucasappvinho.api.model.Enums.OrderStatus;
import com.example.lucasappvinho.api.model.Order;
import com.example.lucasappvinho.api.model.OrderItem;
import com.example.lucasappvinho.api.model.Payment;
import com.example.lucasappvinho.api.model.Representante;
import com.example.lucasappvinho.api.model.User;
import com.example.lucasappvinho.api.model.Vinho;
import com.google.gson.Gson;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepresentantePedidoActivity extends AppCompatActivity {

    private ImageButton btnVoltar;
    private Spinner spinnerClientes, spinnerVinhos, spinnerPagamento;
    private EditText editQuantidade, editPreco;
    private Button btnAdicionarItem, btnSalvarPedido;
    private RecyclerView recyclerViewItens;
    private OrderItemAdapter itemAdapter;
    private List<OrderItem> itensPedido = new ArrayList<>();

    private List<User> listaClientes = new ArrayList<>();
    private List<Vinho> listaVinhos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_novo_pedido);

        // Inicializações
        spinnerClientes = findViewById(R.id.spinnerClientes);
        spinnerVinhos = findViewById(R.id.spinnerVinhos);
        spinnerPagamento = findViewById(R.id.spinnerPagamento);
        editQuantidade = findViewById(R.id.editQuantidade);
        editPreco = findViewById(R.id.editPreco);
        btnAdicionarItem = findViewById(R.id.btnAdicionarItem);
        btnSalvarPedido = findViewById(R.id.btnSalvarPedido);
        recyclerViewItens = findViewById(R.id.recyclerViewItens);


        recyclerViewItens.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new OrderItemAdapter(itensPedido);
        recyclerViewItens.setAdapter(itemAdapter);

        // Carregamento de dados
        carregarClientes();
        carregarVinhos();
        carregarFormasPagamento();

        btnAdicionarItem.setOnClickListener(v -> adicionarItem());
        btnSalvarPedido.setOnClickListener(v -> salvarPedido());

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(RepresentantePedidoActivity.this, PedidosActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void carregarClientes() {
        Api.getUserEndpoint().getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaClientes = response.body();
                    List<String> nomes = new ArrayList<>();
                    for (User u : listaClientes) {
                        nomes.add(u.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(RepresentantePedidoActivity.this, android.R.layout.simple_spinner_item, nomes);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerClientes.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(RepresentantePedidoActivity.this, "Erro ao carregar clientes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarVinhos() {
        Api.getVinhoEndpoint().getAllWines().enqueue(new Callback<List<Vinho>>() {
            @Override
            public void onResponse(Call<List<Vinho>> call, Response<List<Vinho>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaVinhos = response.body();
                    List<String> nomes = new ArrayList<>();
                    for (Vinho v : listaVinhos) {
                        nomes.add(v.getNome());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(RepresentantePedidoActivity.this, android.R.layout.simple_spinner_item, nomes);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerVinhos.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Vinho>> call, Throwable t) {
                Toast.makeText(RepresentantePedidoActivity.this, "Erro ao carregar vinhos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarFormasPagamento() {
        ArrayAdapter<OrderStatus> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                OrderStatus.values()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPagamento.setAdapter(adapter);
    }

    private void adicionarItem() {
        int posVinho = spinnerVinhos.getSelectedItemPosition();
        String quantidadeStr = editQuantidade.getText().toString().trim();
        String precoStr = editPreco.getText().toString().trim();

        if (posVinho >= 0 && posVinho < listaVinhos.size() && !quantidadeStr.isEmpty() && !precoStr.isEmpty()) {
            Vinho vinhoSelecionado = listaVinhos.get(posVinho);
            int quantidade = Integer.parseInt(quantidadeStr);
            double preco = Double.parseDouble(precoStr);

            OrderItem item = new OrderItem();
            item.setVinho(vinhoSelecionado);
            item.setQuantity(quantidade);
            item.setPrice(preco);

            itensPedido.add(item);
            itemAdapter.notifyDataSetChanged();

            editQuantidade.setText("");
            editPreco.setText("");
        } else {
            Toast.makeText(this, "Preencha os campos de quantidade e preço", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarPedido() {
        int posCliente = spinnerClientes.getSelectedItemPosition();
        if (posCliente < 0 || posCliente >= listaClientes.size()) {
            Toast.makeText(this, "Selecione um cliente", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Sessao.idUsuarioLogado == null) {
            Toast.makeText(this, "Erro: Usuário não logado.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (itensPedido.isEmpty()) {
            Toast.makeText(this, "Adicione ao menos um item ao pedido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Preencher dados do pedido
        User clienteSelecionado = listaClientes.get(posCliente);
        Order novoPedido = new Order();
        novoPedido.setClient(clienteSelecionado);

        Representante representante = new Representante();
        representante.setId(Sessao.idUsuarioLogado);
        novoPedido.setRepresentante(representante);

        novoPedido.setItems(itensPedido);

        // Definir o status do pedido
        OrderStatus statusSelecionado = (OrderStatus) spinnerPagamento.getSelectedItem();
        novoPedido.setOrderStatus(statusSelecionado.name());

        // Adicionar pagamento (fixo ou simulado)
        Payment pagamento = new Payment();
        pagamento.setId(1L); // Se o backend gerar, pode deixar como null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ZonedDateTime nowLocal = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
            Instant instantUTC = nowLocal.toInstant();  // converte para UTC mazena em UTC para manter padrão global e evitar confusão
            pagamento.setMoment(instantUTC.toString());
        }
        novoPedido.setPayment(pagamento);

        Log.d("API-JSON", new Gson().toJson(novoPedido));

        Api.getOrderEndpoint().criarPedido(novoPedido).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RepresentantePedidoActivity.this, "Pedido criado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RepresentantePedidoActivity.this, "Erro ao criar pedido: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("API", "Erro: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(RepresentantePedidoActivity.this, "Falha na conexão", Toast.LENGTH_SHORT).show();
                Log.e("API", "Falha: ", t);
            }
        });
    }
}
