package com.LembretesVictor.projeto;

public class Lembrete {

    private String nome;
    private String conteudo;
    private String dataAtual;
    private String dataPrev;

    public Lembrete(String nome, String conteudo, String dataAtual, String dataPrev){
        this.nome = nome;
        this.conteudo = conteudo;
        this.dataAtual = dataAtual;
        this.dataPrev = dataPrev;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(String dataAtual) {
        this.dataAtual = dataAtual;
    }

    public String getDataPrev() {
        return dataPrev;
    }

    public void setDataPrev(String dataPrev) {
        this.dataPrev = dataPrev;
    }
}
