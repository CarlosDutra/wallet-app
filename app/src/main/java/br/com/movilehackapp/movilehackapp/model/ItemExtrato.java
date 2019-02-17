package br.com.movilehackapp.movilehackapp.model;

public class ItemExtrato {
    private String data;
    private String categoria;
    private String descricao;
    private double valor;

    public ItemExtrato(String data, String categoria, String descricao, double valor) {
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
