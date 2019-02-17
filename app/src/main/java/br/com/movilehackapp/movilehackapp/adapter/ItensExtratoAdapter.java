package br.com.movilehackapp.movilehackapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.movilehackapp.movilehackapp.R;
import br.com.movilehackapp.movilehackapp.model.ItemExtrato;

public class ItensExtratoAdapter extends RecyclerView.Adapter<ItensExtratoAdapter.MyViewHolder> {

    private List<ItemExtrato> listaItensExtrato;
    private Context context;

    public ItensExtratoAdapter(List<ItemExtrato> listaItensExtrato, Context context) {
        this.listaItensExtrato = listaItensExtrato;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_item_extrato, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ItemExtrato itemExtrato = listaItensExtrato.get(i);

        myViewHolder.textDescricao.setText(itemExtrato.getDescricao());
        myViewHolder.textCategoria.setText(itemExtrato.getCategoria());
        myViewHolder.textData.setText(itemExtrato.getData());
        myViewHolder.textValor.setText("R$" + String.valueOf(itemExtrato.getValor()));

    }

    @Override
    public int getItemCount() {
        return listaItensExtrato.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textCategoria;
        TextView textDescricao;
        TextView textValor;
        TextView textData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textCategoria = itemView.findViewById(R.id.textCategoria);
            textDescricao = itemView.findViewById(R.id.textDescricao);
            textValor = itemView.findViewById(R.id.textValor);
            textData = itemView.findViewById(R.id.textData);

        }
    }

}
