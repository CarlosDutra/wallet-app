package br.com.movilehackapp.movilehackapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.movilehackapp.movilehackapp.R;
import br.com.movilehackapp.movilehackapp.adapter.ProdutosAdapter;
import br.com.movilehackapp.movilehackapp.helper.GetNoticeDataService;
import br.com.movilehackapp.movilehackapp.helper.RecyclerItemClickListener;
import br.com.movilehackapp.movilehackapp.helper.RetrofitInstance;
import br.com.movilehackapp.movilehackapp.model.Produto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComprarServicosActivity extends AppCompatActivity {

    private List<Produto> listaProdutos = new ArrayList<>();
    private RecyclerView recyclerProdutos;
    private ProdutosAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar_servicos);

        //Configurações toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Elementos da interface
        recyclerProdutos = findViewById(R.id.recyclerProdutos);
        progressBar = findViewById(R.id.progressBar);

        //Configurações iniciais
        configurarRecyclerProdutos();
    }

    private void configurarRecyclerProdutos(){
        //Layout e adapter
        adapter = new ProdutosAdapter(listaProdutos, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true);
        recyclerProdutos.setAdapter(adapter);

        //Evento de clique
        recyclerProdutos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerProdutos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Produto produto = listaProdutos.get(position);
                                Intent i = new Intent(ComprarServicosActivity.this, EncomendarActivity.class);
                                i.putExtra("dadosProduto", produto);
                                startActivity(i);
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

    @Override
    protected void onStart() {
        super.onStart();

        //Recuperar produtos da base de dados
        recuperarProdutos();

    }

    private void recuperarProdutos(){

        MyAsyncTask task = new MyAsyncTask();
        task.execute();

        //Limpar produtos antigos para não repetir
        /*listaProdutos.clear();

        //Requisição HTTP da base de dados
        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);

        Call<List<Produto>> call = service.listaProdutos();

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                List<Produto> listaProdutosRecuperados = response.body();

                if(listaProdutosRecuperados != null && !listaProdutosRecuperados.isEmpty()){
                    for(Produto produto: listaProdutosRecuperados){
                        listaProdutos.add(produto);
                    }

                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(ComprarServicosActivity.this,
                            "Não foram encontrados produtos.",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {

                Toast.makeText(ComprarServicosActivity.this,
                        "Não foi possível obter os produtos. Erro HTTP GET.",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        progressBar.setVisibility(View.GONE);
        recyclerProdutos.setVisibility(View.VISIBLE);*/
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void>{

        private List<Produto> produtos;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            exibirProgress(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //Limpar produtos antigos para não repetir
            listaProdutos.clear();

            //Requisição HTTP da base de dados
            GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);
            Call<List<Produto>> call = service.listaProdutos();

            try {
                produtos = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(produtos != null && !produtos.isEmpty()){
                for(Produto prod : produtos){
                    listaProdutos.add(prod);
                }
            }
            else{
                Toast.makeText(ComprarServicosActivity.this,
                        "Não foram encontrados produtos.",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            adapter.notifyDataSetChanged();
            exibirProgress(false);
        }

        private void exibirProgress(boolean exibirProgress) {
            progressBar.setVisibility(exibirProgress ? View.VISIBLE : View.GONE);
            recyclerProdutos.setVisibility(!exibirProgress ? View.VISIBLE : View.GONE);
        }

    }

}
