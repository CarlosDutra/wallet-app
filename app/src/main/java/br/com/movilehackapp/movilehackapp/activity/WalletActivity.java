package br.com.movilehackapp.movilehackapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.movilehackapp.movilehackapp.R;
import br.com.movilehackapp.movilehackapp.adapter.ItensExtratoAdapter;
import br.com.movilehackapp.movilehackapp.adapter.ServicosAdapter;
import br.com.movilehackapp.movilehackapp.helper.RecyclerItemClickListener;
import br.com.movilehackapp.movilehackapp.model.ItemExtrato;
import br.com.movilehackapp.movilehackapp.model.Servico;
import br.com.movilehackapp.movilehackapp.model.Usuario;

public class WalletActivity extends AppCompatActivity {

    private RecyclerView recyclerServicos;
    private RecyclerView recyclerExtrato;
    private TextView textSaldo;
    private List<Servico> servicos = new ArrayList<>();
    private List<ItemExtrato> itensExtrato = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        //Configurando nome da toolbar
        getSupportActionBar().setTitle(R.string.title_activity_wallet);

        //Identificando elementos da interface
        recyclerServicos = findViewById(R.id.recyclerServicos);
        recyclerExtrato = findViewById(R.id.recyclerExtrato);
        textSaldo = findViewById(R.id.textSaldo);

        //Configurando dados do usuário (fixo para o protótipo)
        Usuario.setBuyerId("10ba837253b24965823e533686e81d69");
        Usuario.setNome("Joãozinho");

        //Configurações iniciais
        configurarRecyclerViewServicos();
        configurarRecyclerViewExtrato();
    }

    private void configurarRecyclerViewServicos(){
        //Adicionar itens no recycler view
        adicionarItensRecyclerViewServicos();

        //Linear layout para a escolha do serviço
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);

        //Adapter
        ServicosAdapter adapter = new ServicosAdapter(servicos, getApplicationContext());
        recyclerServicos.setAdapter(adapter);
        recyclerServicos.setLayoutManager(layoutManager);
        recyclerServicos.setHasFixedSize(true);

        //Evento de clique no recyclerview
        recyclerServicos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerServicos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Servico servicoSelecionado = servicos.get(position);
                                abrirServicoEscolhido(servicoSelecionado);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

    }

    private void configurarRecyclerViewExtrato(){
        //Adicionar itens no recycler view
        adicionarItensRecyclerViewExtrato();

        //Linear layout para a escolha do serviço
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.VERTICAL);

        //Adapter
        ItensExtratoAdapter adapter = new ItensExtratoAdapter(itensExtrato, getApplicationContext());
        recyclerExtrato.setAdapter(adapter);
        recyclerExtrato.setLayoutManager(layoutManager);
        recyclerExtrato.setHasFixedSize(true);
    }

    private void adicionarItensRecyclerViewServicos(){
        //Isso será fixo, mas também pode ser dinâmico
        Servico s;
        s = new Servico("Comprar produtos", R.drawable.ic_list_servicos_preto_40dp);
        servicos.add(s);

        s = new Servico("Pagar boleto", R.drawable.ic_boleto_preto_40dp);
        servicos.add(s);

        s = new Servico("Enviar dinheiro", R.drawable.ic_enviar_dinheiro_black_40dp);
        servicos.add(s);

        s = new Servico("Exemplo");
        servicos.add(s);
    }

    private void adicionarItensRecyclerViewExtrato(){
        //A ideia é obter por request HTTP, mas iremos usar o exemplo abaixo
        ItemExtrato item;

        item = new ItemExtrato("16/02/2019 19:33",
                "Video-game",
                "Playstation 4",
                1700);
        itensExtrato.add(item);
    }

    //Por enquanto, limitaremos os servicos a pagar boleto e comprar um produto
    private void abrirServicoEscolhido(Servico servico){
        Intent i;

        if(servico.getNome().equals("Comprar produtos")){
            i = new Intent(WalletActivity.this, ComprarServicosActivity.class);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(i);
        }
        else if (servico.getNome().equals("Enviar dinheiro")){
            i = new Intent(WalletActivity.this, EnviarDinheiroActivity.class);
            startActivity(i);
        }
        else if (servico.getNome().equals("Pagar boleto")){
            i = new Intent(WalletActivity.this, PagarBoletoActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(WalletActivity.this,
                    "Erro: serviço não existe.",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
