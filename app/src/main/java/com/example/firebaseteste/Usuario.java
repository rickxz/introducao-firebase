package com.example.firebaseteste;

public class Usuario {
    private String nome;
    private String sobrenome;
    private int idade;
    public Usuario ( String n, String s, int i ) {
        this.nome = n;
        this.sobrenome = s;
        this.idade = i;
    }

    public Usuario() {

    }

    // É obrigatório ter todos os getters e setters!!
    // Android Studio pode criar automaticamente pra você.
    // menu Code | Generate | Getter and Setter
    // Marca todos os atributos, clica OK.

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }

    public void setSobrenome(String s) { this.sobrenome = s; }

    public int getIdade() { return idade; }

    public void setIdade(int idade) { this.idade = idade; }

}