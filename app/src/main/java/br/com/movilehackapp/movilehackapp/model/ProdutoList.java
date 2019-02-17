package br.com.movilehackapp.movilehackapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProdutoList {

    @SerializedName("compra_list")
    private ArrayList<Produto> compraList;

    public ArrayList<Produto> getNoticeArrayList() {
        return compraList;
    }

    public void setNoticeArrayList(ArrayList<Produto> compraList) {
        this.compraList = compraList;
    }

}
