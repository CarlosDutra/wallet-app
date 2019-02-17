package br.com.movilehackapp.movilehackapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.movilehackapp.movilehackapp.R;
import br.com.movilehackapp.movilehackapp.model.Servico;

public class ServicosAdapter extends RecyclerView.Adapter<ServicosAdapter.MyViewHolder> {

    private List<Servico> listaServicos;
    private Context context;

    public ServicosAdapter(List<Servico> listaServicos, Context context) {
        this.listaServicos = listaServicos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_servicos, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Servico servico = listaServicos.get(i);

        myViewHolder.nomeServico.setText(servico.getNome());
        myViewHolder.imagemServico.setImageResource(servico.getImagem());

    }

    @Override
    public int getItemCount() {
        return listaServicos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeServico;
        ImageView imagemServico;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeServico = itemView.findViewById(R.id.nomeServico);
            imagemServico = itemView.findViewById(R.id.imagemServico);

        }
    }
}
