package com.tgid.models;

import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.List;

public class Venda {
    private Usuario usuario;
    private List<ItemVenda> itens;
    private OffsetDateTime dataHoraVenda;

    public Venda(Usuario usuario, List<ItemVenda> itens) {
        this.usuario = usuario;
        this.itens = itens;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public double calcularTotalVenda() {
        return itens.stream().mapToDouble(ItemVenda::calcularTotal).sum();
    }

    public void finalizarVenda() {
        this.dataHoraVenda = OffsetDateTime.now();
        System.out.println("Venda finalizada com sucesso!");
        toString();
        System.out.println("Data e hora da venda: " + dataHoraVenda);
    }

    @Override
    public String toString() {
        StringBuilder resumo = new StringBuilder("Venda para " + usuario.getNome() + "\n");
        for (ItemVenda item : itens) {
            resumo.append(item).append("\n");
        }
        DecimalFormat df = new DecimalFormat("0.00");
        resumo.append("Total da Venda: R$ ").append(df.format(calcularTotalVenda()));
        return resumo.toString();
    }
}
