package com.tgid;

import com.tgid.services.ProdutoLoader;
import com.tgid.models.*;
import com.tgid.services.BuscadorCEP;
import com.tgid.services.VerifyCPF;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import static java.util.Objects.isNull;

public class LojaOnline {
    public static Scanner sc = new Scanner(System.in);
    public static List<Produto> produtos = ProdutoLoader.carregarProdutos();
    public static List<ItemVenda> carrinho = new java.util.ArrayList<>();
    public static Venda venda;
    public static List<Venda> pedidosConfirmados = new java.util.ArrayList<>();
    public static Usuario usuario;

    public static void main(String[] args) throws IOException, InterruptedException {
        menuInicial();
        sc.close();
    }

    public static void menuInicial() throws IOException, InterruptedException {
        System.out.println("""
                **********************************
                *****Bem vindo à loja online!*****
                **********************************""");
        System.out.println("""
                Selecione uma opção:
                1 - Visualizar produtos
                2 - Conferir carrinho de compras
                3 - Finalizar compra
                4 - Visualizar pedidos confirmados
                5 - Cadastrar usuário
                6 - Sair""");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1 -> visualizarProdutos();
            case 2 -> conferirCarrinho();
            case 3 -> {
                finalizarCompra();
                menuInicial();
            }
            case 4 -> {
                if (pedidosConfirmados.isEmpty()) {
                    System.out.println("Nenhum pedido confirmado até o momento.");
                } else {
                    System.out.println("Pedidos confirmados:");
                    for (Venda pedido : pedidosConfirmados) {
                        System.out.println(pedido);
                    }
                }
                System.out.println("Tecle para continuar...");
                sc.nextLine();
                sc.nextLine();
                menuInicial();
            }
            case 5 -> {
                cadastrarUsuario();
                menuInicial();
            }
            case 6 -> {
                System.out.println("Obrigado por visitar nossa loja!");
                System.exit(0);
            }
            default -> {
                System.out.println("Opção inválida. Tente novamente.");
                menuInicial();
            }
        }
    }

    private static void visualizarProdutos() throws IOException, InterruptedException {
        System.out.println("""
                ---------------------------------------------------------------
                Lista de Produtos Disponíveis:""");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println("""
                Selecione uma opção:
                1 - Adicionar produto ao carrinho
                2 - Visualizar carrinho de compras
                3 - Voltar ao menu inicial""");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1 -> {
                adicionarProdutoAoCarrinho();
                visualizarProdutos();
            }
            case 2 -> conferirCarrinho();
            case 3 -> menuInicial();
            default -> {
                System.out.println("Opção inválida. Tente novamente.");
                visualizarProdutos();
            }
        }
    }

    private static void adicionarProdutoAoCarrinho() {
        System.out.println("Digite o código do produto que deseja adocionar ao carrinho: ");
        int codigoProduto = sc.nextInt();
        for (Produto produto : produtos){
            if (produto.getIdProduto() == codigoProduto){
                System.out.println("Adicionando o produto ao carrinho: " + produto);
                System.out.println("Digite a quantidade que deseja adicionar: ");
                int quantidade = sc.nextInt();
                ItemVenda itemVenda = new ItemVenda(produto);
                if (itemVenda.verificarEstoque(quantidade)){
                    carrinho.add(itemVenda);
                    System.out.println("Produto adicionado ao carrinho com sucesso: ");
                    System.out.println(itemVenda);
                    System.out.println("Tecle para continuar...");
                    sc.nextLine();
                    sc.nextLine();
                    return;
                } else {
                    System.out.println("Quantidade solicitada não disponível em estoque.");
                    return;
                }
            }
        }
        System.out.println("Produto não encontrado. Tente novamente.");
        adicionarProdutoAoCarrinho();
    }

    private static void conferirCarrinho() throws IOException, InterruptedException {
        if (carrinho.isEmpty()) {
            System.out.println("Seu carrinho está vazio.");
            return;
        }
        System.out.println("Itens adcionados no seu carrinho:");
        for (ItemVenda item : carrinho) {
            System.out.println(item);
        }
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Total do carrinho: R$ " + df.format(carrinho.stream().mapToDouble(ItemVenda::calcularTotal).sum()));
        System.out.println("""
                Selecione uma opção:
                1 - Remover item do carrinho
                2 - Finalizar compra
                3 - Voltar ao menu inicial""");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1 -> {
                removerItemDoCarrinho();
                conferirCarrinho();
            }
            case 2 -> {
                finalizarCompra();
                menuInicial();
            }
            case 3 -> menuInicial();
            default -> {
                System.out.println("Opção inválida. Tente novamente.");
                conferirCarrinho();
            }
        }
    }

    private static void removerItemDoCarrinho() {
        System.out.println("Digite o código do produto que deseja remover do carrinho: ");
        int codigoProduto = sc.nextInt();
        for (ItemVenda item : carrinho) {
            if (item.getProduto().getIdProduto() == codigoProduto) {
                carrinho.remove(item);
                System.out.println("Produto removido do carrinho com sucesso: ");
                System.out.println(item);
                System.out.println("Tecle para continuar...");
                sc.nextLine();
                sc.nextLine();
                return;
            }
        }
        System.out.println("Produto não encontrado no carrinho. Tente novamente.");
        removerItemDoCarrinho();
    }

    private static void finalizarCompra() throws IOException, InterruptedException {
        if (carrinho.isEmpty()) {
            System.out.println("Seu carrinho está vazio. Não é possível finalizar a compra.");
            return;
        }
        if (isNull(usuario)) {
            System.out.println("Para finalizar a compra, primeiramente, informe os dados do usuário:");
            cadastrarUsuario();
        }
        System.out.println("Finalizando a compra...");
        if (!isNull(usuario)) {
            venda = new Venda(usuario, carrinho);
            System.out.println("Resumo da compra:");
            System.out.println(venda);
            System.out.println("Deseja confirmar a compra? (S/N)");
            if (sc.next().equalsIgnoreCase("S")) {
                System.out.println("Boleto emitido com sucesso! (Emissão fictícia)");
                venda.finalizarVenda();
                pedidosConfirmados.add(venda);
                atualizarEstoque();
                System.out.println("Obrigado por comprar conosco!");
            }
        } else {
            System.out.println("Usuário não cadastrado. Não é possível finalizar a compra.");
        }
    }

    private static void cadastrarUsuario() throws IOException, InterruptedException {
        sc.nextLine(); // Limpar o buffer do scanner
        System.out.println("Informe o nome do usuário: ");
        String nome = sc.nextLine();
        System.out.println("Informe o email do usuário: ");
        String email = sc.nextLine();

        VerifyCPF cpf;
        do {
            System.out.println("\nDigite seu CPF, sem hifen:");
            String inputCPF = sc.next();
            inputCPF = inputCPF.substring(0,11);
            cpf = new VerifyCPF(inputCPF);
            System.out.println(!cpf.isCPF()? "\nNúmero de CPF inválido! Favor, digite novamente:": "CPF verificado!");
        }while(!cpf.isCPF());

        sc.nextLine(); // Limpar o buffer do scanner após a leitura do CPF
        Cep cepValidado;
        do {
            System.out.println("Informe seu CEP valido de entrega (sem hifen ou espacos): ");
            String cep = sc.nextLine();
             cepValidado = new BuscadorCEP().buscarCEP(cep);
        } while (isNull(cepValidado));

        usuario = new Usuario(nome, cpf.getCPF(), email, cepValidado);
        System.out.println("Confirmacao dos dados do usuário: ");
        System.out.println(usuario);
        System.out.println("Tecle para continuar...");
        sc.nextLine();
    }

    public static void atualizarEstoque(){
        for (ItemVenda item : venda.getItens()){
            produtos.stream()
                .filter(produto -> produto.getIdProduto() == item.getProduto().getIdProduto())
                .findFirst()
                .ifPresent(produto -> produto.reduzirEstoque(item.getQuantidade()));
        }
    }
}