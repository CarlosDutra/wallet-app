package br.com.movilehackapp.movilehackapp.activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import br.com.movilehackapp.movilehackapp.R;
import br.com.movilehackapp.movilehackapp.helper.GetNoticeDataService;
import br.com.movilehackapp.movilehackapp.helper.RetrofitInstance;
import br.com.movilehackapp.movilehackapp.model.Compra;
import br.com.movilehackapp.movilehackapp.model.Produto;
import br.com.movilehackapp.movilehackapp.model.Usuario;
import br.com.movilehackapp.movilehackapp.util.MaskEditUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncomendarActivity extends AppCompatActivity {

    private Produto produtoEscolhido;

    private TextView textTituloProdutoEscolhido,
            textPrecoMedioProdutoEscolhido,
            textVezesEncomendadasProdutoEscolhido,
            textValorResultado,
            textRendimentoPrevisto;

    private ImageView imagemProdutoEscolhido;

    private TextInputEditText editValorDispostoAPagar,
            editDataLimite;

    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encomendar);

        //Elementos da interface
        textTituloProdutoEscolhido = findViewById(R.id.textTituloProdutoEscolhido);
        textPrecoMedioProdutoEscolhido = findViewById(R.id.textPrecoMedioProdutoEscolhido);
        textVezesEncomendadasProdutoEscolhido = findViewById(R.id.textVezesEncomendadasProdutoEscolhido);
        textValorResultado = findViewById(R.id.textValorResultado);
        textRendimentoPrevisto = findViewById(R.id.textRendimentoPrevisto);
        editValorDispostoAPagar = findViewById(R.id.editValorDispostoAPagar);
        imagemProdutoEscolhido = findViewById(R.id.imagemProdutoEscolhido);
        editDataLimite = findViewById(R.id.editDataLimite);

        //Valores default de preenchimento para fins de prototipagem
        editValorDispostoAPagar.setText("10");
        editDataLimite.setText("17/03/2019");
        textValorResultado.setText("Preço total: R$15.00");

        //Formatação do editDataLimite
        editDataLimite.addTextChangedListener(MaskEditUtil.mask(editDataLimite, MaskEditUtil.FORMAT_DATE));

        //Obter dados do produto via Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //Atualizar dados do produto em questão para preparar compra
            if(bundle.containsKey("dadosProduto")){
                produtoEscolhido = (Produto) bundle.getSerializable("dadosProduto");

                String url = produtoEscolhido.getImage();
                if(url != null){
                    Uri uri = Uri.parse(url);
                    Glide.with(this)
                            .load(uri)
                            .into(imagemProdutoEscolhido);
                }
                else{
                    imagemProdutoEscolhido.setImageResource(R.drawable.ic_android_branco_40dp);
                }

                textTituloProdutoEscolhido.setText(produtoEscolhido.getName());
                textPrecoMedioProdutoEscolhido.setText(decimalFormat.format(produtoEscolhido.getAverage()));
                textVezesEncomendadasProdutoEscolhido.setText(String.valueOf(produtoEscolhido.getCustomers()));
            }
        }
        else{
            Toast.makeText(EncomendarActivity.this,
                    "Houve um problema com o produto (null object). Tente novamente.",
                    Toast.LENGTH_SHORT)
                    .show();

            finish();
        }

        //Listener no editText
        editValorDispostoAPagar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence == null || charSequence.toString().equals("")){
                    textValorResultado.setText("Preço total: R$0.00");
                    textRendimentoPrevisto.setText("R$0.00");
                }
                else{
                    double value = Double.parseDouble(charSequence.toString());

                    String valorResultado = "Preço total: R$" + decimalFormat.format(value + 5);
                    String rendimentoPrevisto = "R$" + decimalFormat.format(value*0.0005);

                    textValorResultado.setText(valorResultado);
                    textRendimentoPrevisto.setText(rendimentoPrevisto);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void encomendar(View view){

        //Recuperar valores e enviar o request HTTP
        String valorDispostoAPagar = editValorDispostoAPagar.getText().toString();
        String dataLimite = editDataLimite.getText().toString();
        String[] diaMesAno = dataLimite.split("/");
        dataLimite = diaMesAno[2] + "-" + diaMesAno[1] + "-" + diaMesAno[0];

        final Compra compra = new Compra(
                Usuario.getBuyerId(),
                Double.parseDouble(valorDispostoAPagar),
                dataLimite
        );

        //Verificação de campos inválidos
        if(valorDispostoAPagar.equals("")){
            Toast.makeText(this,
                    "Insira um valor máximo que você esteja disposto a pagar.",
                    Toast.LENGTH_SHORT)
                    .show();
        }
        else if(dataLimite.equals("")){
            Toast.makeText(this,
                    "Insira uma data máxima do crédito.",
                    Toast.LENGTH_SHORT)
                    .show();
        }
        else{
            //AlertDialog
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);

            //Configura título e mensagem
            dialog.setTitle("Atenção");
            dialog.setMessage("Recebemos sua encomenda." +
                    "\nO dinheiro referente ao valor que você pode pagar será debitado de seu cartão." +
                    "\nAssim que encontrarmos um vendedor para sua compra, seu produto será enviado diretamente para sua casa, e estornaremos o valor da diferença se houver, junto de um rendimento.");

            //Configura o cancelamento (determina se o usuário
            //pode fugir ou não do AlertDialog)
            dialog.setCancelable(false);

            //Configura ações para botão sim ou não
            dialog.setPositiveButton("Estou ciente", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MyAsyncTask task = new MyAsyncTask();
                    task.execute(compra);
                }
            });

            dialog.create();
            dialog.show();
        }
    }

    class MyAsyncTask extends AsyncTask<Compra, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Toast.makeText(EncomendarActivity.this, "Enviando pedido de encomenda...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Compra... compras) {


            //Requisição HTTP da base de dados
            GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);

            Compra compra = compras[0];
            Call<Compra> call = service.criarCompra(produtoEscolhido.getId(), compra);

            call.enqueue(new Callback<Compra>() {
                @Override
                public void onResponse(Call<Compra> call, Response<Compra> response) {
                    Toast.makeText(EncomendarActivity.this,
                            "Encomenda realizada com sucesso!",
                            Toast.LENGTH_SHORT)
                            .show();
                }

                @Override
                public void onFailure(Call<Compra> call, Throwable t) {

                    Toast.makeText(EncomendarActivity.this,
                            "Não foi possível encomendar. Erro HTTP POST.",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aBoolean) {
            super.onPostExecute(aBoolean);

            //Fechar janela
            finish();

        }
    }

}
