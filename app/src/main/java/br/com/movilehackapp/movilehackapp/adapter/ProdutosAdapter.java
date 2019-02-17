package br.com.movilehackapp.movilehackapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

import br.com.movilehackapp.movilehackapp.R;
import br.com.movilehackapp.movilehackapp.model.Produto;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.MyViewHolder> {

    private List<Produto> listaProdutos;
    private Context context;

    public ProdutosAdapter(List<Produto> listaProdutos, Context context) {
        this.listaProdutos = listaProdutos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_produtos, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Produto produto = listaProdutos.get(i);

        if(produto.getImage() != null){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.ic_android_preto_40dp)
                    .fitCenter();

            Uri uri = Uri.parse(produto.getImage());
            Glide.with(context)
            .load(uri)
            .apply(options)
            .into(myViewHolder.imagemProduto);
        }

        myViewHolder.descricaoProduto.setText(produto.getDescription());
        myViewHolder.nomeProduto.setText(produto.getName());

    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeProduto;
        TextView descricaoProduto;
        ImageView imagemProduto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProduto = itemView.findViewById(R.id.textNomeProduto);
            descricaoProduto = itemView.findViewById(R.id.textDescricaoProduto);
            imagemProduto = itemView.findViewById(R.id.imagemProduto);

        }
    }
}
