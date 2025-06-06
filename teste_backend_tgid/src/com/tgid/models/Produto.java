package com.tgid.models;

import java.text.DecimalFormat;

public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;

    public Produto() {
        // Construtor padrão
    }

    public Produto(int idProduto, String nome, String descricao, double preco, int quantidade) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void reduzirEstoque(int qtdeVendida) {
        this.quantidadeEstoque -= qtdeVendida;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "-----Código do produto: " + idProduto + "\n    Produto: " + nome + ", Descricao: " + descricao + ", Preço: R$ " + df.format(preco) + ", Estoque: " + quantidadeEstoque + "\n";
    }
}