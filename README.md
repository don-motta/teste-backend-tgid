# Projeto: Teste Backend TGID

## Descrição
O projeto `teste_backend_tgid` é uma aplicação backend desenvolvida em Java, com objetivo de ser uma aplicação simples simulando uma loja online.

## Funcionalidades

### 1. **Gerenciamento de Vendas**
- Classe: `Venda`
- Calcula o total de uma venda com base nos itens vendidos.
- Finaliza a venda, registrando a data e hora.
- Método principal: `calcularTotalVenda()` para calcular o total e `finalizarVenda()` para concluir a venda.

### 2. **Itens de Venda**
- Classe: `ItemVenda`
- Representa um item vendido, com informações como produto, quantidade e preço.
- Método principal: `calcularTotal()` para calcular o valor total do item.

### 3. **Usuários**
- Classe: `Usuario`
- Representa um usuário com propriedades como nome, CPF, email e endereço através de CEP.

### 4. **Produtos**
- Classe: `Produto`
- Representa um produto com propriedades como nome, descrição, preço e quantidade em estoque.
- Permite reduzir o estoque após uma venda.

## Funcionalidades Adicionais

### 1. **Validação de CPF**
- Classe: `VerifyCPF`
- Verifica se um CPF é válido com base nos dígitos verificadores.
- Método principal: `isCPF()`, que retorna `true` para CPFs válidos e `false` para inválidos.

### 2. **Manipulação de CEP**
- Classe: `Cep`
- Permite armazenar e exibir informações de um CEP, como logradouro, complemento, unidade, bairro e localidade.
- Conexão com API `ViaCEP` para a consulta de CEP valido e busca de endereço.


## Propriedades e Configurações
- **Java Version**: Nesse projeto foi utilizado JDK 22.
- **Maven Compiler Plugin**:
  ```xml
  <properties>
      <maven.compiler.source>21</maven.compiler.source>
      <maven.compiler.target>21</maven.compiler.target>
  </properties>
  
- **Dependências**:
    ```xml
    <dependency>
       <groupId>com.fasterxml.jackson.core</groupId>
       <artifactId>jackson-databind</artifactId>
       <version>2.19.0</version>
    </dependency>
    <dependency>
       <groupId>com.google.code.gson</groupId>
       <artifactId>gson</artifactId>
       <version>2.13.1</version>
    </dependency>