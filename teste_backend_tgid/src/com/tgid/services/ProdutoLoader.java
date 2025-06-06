package com.tgid.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgid.models.Produto;
import java.io.IOException;
import java.util.List;

public class ProdutoLoader {
    public static List<Produto> carregarProdutos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Produto> produtos = objectMapper.readValue(
                    ProdutoLoader.class.getClassLoader().getResourceAsStream("products.json"),
                    new TypeReference<List<Produto>>() {});
            if (produtos != null) {
                System.out.println("Produtos carregados com sucesso! ");
            } else {
                System.out.println("Falha ao carregar os produtos.");
            }
            return produtos;
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
            return null;
        }
    }
}
