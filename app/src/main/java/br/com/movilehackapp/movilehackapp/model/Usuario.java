package br.com.movilehackapp.movilehackapp.model;

public class Usuario {
    private static String nome;
    private static String buyerId;
    private static String endereco;

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Usuario.nome = nome;
    }

    public static String getBuyerId() {
        return buyerId;
    }

    public static void setBuyerId(String idUsuario) {
        Usuario.buyerId = idUsuario;
    }

    public static String getEndereco() {
        return endereco;
    }

    public static void setEndereco(String endereco) {
        Usuario.endereco = endereco;
    }
}
