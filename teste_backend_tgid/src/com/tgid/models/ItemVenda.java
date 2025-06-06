package com.tgid.models;

import java.text.DecimalFormat;

public class ItemVenda {
    private Produto produto;
    private int quantidade;

    public ItemVenda(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double calcularTotal() {
        return produto.getPreco() * quantidade;
    }

    public boolean verificarEstoque(int quantidade) {
        if (produto.getQuantidadeEstoque() < quantidade){
           return false;
        }
        this.quantidade = quantidade;
        return true;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return produto.getNome() + " x" + quantidade + " - Total: R$ " + df.format(calcularTotal());
    }
}