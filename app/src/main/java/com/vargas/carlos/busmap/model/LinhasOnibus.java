package com.vargas.carlos.busmap.model;

public class LinhasOnibus {

    private int id;
    private String nome;
    private String trajeto;
    private String mapa;

    public LinhasOnibus(int id, String nome, String trajeto, String mapa) {
        this.id = id;
        this.nome = nome;
        this.trajeto = trajeto;
        this.mapa = mapa;
    }

    public LinhasOnibus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(String trajeto) {
        this.trajeto = trajeto;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
