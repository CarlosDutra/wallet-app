package br.com.movilehackapp.movilehackapp.model;

import br.com.movilehackapp.movilehackapp.R;

public class Servico {
    private String nome;
    private int imagem;

    public Servico(String nome, int imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public Servico(String nome){
        this.nome = nome;
        this.imagem = R.drawable.ic_android_branco_40dp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
