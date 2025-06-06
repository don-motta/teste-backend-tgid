package com.tgid.models;

public class Usuario {
    private String nome, cpf, email;
    private Cep cep;

    public Usuario(String nome, String cpf, String email, Cep cep) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Email: " + email + ", CEP: " + cep;
    }
}
