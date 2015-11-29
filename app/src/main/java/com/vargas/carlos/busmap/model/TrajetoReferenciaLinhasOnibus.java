package com.vargas.carlos.busmap.model;

public class TrajetoReferenciaLinhasOnibus {

    private int id;
    private ReferenciaLinhasOnibus referenciaLinhasOnibus;
    private String bairroInicial;
    private String centro;
    private String bairroFinal;

    public TrajetoReferenciaLinhasOnibus(int id, ReferenciaLinhasOnibus referenciaLinhasOnibus, String bairroInicial, String centro, String bairroFinal) {
        this.id = id;
        this.referenciaLinhasOnibus = referenciaLinhasOnibus;
        this.bairroInicial = bairroInicial;
        this.centro = centro;
        this.bairroFinal = bairroFinal;
    }

    public TrajetoReferenciaLinhasOnibus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReferenciaLinhasOnibus getReferenciaLinhasOnibus() {
        return referenciaLinhasOnibus;
    }

    public void setReferenciaLinhasOnibus(ReferenciaLinhasOnibus referenciaLinhasOnibus) {
        this.referenciaLinhasOnibus = referenciaLinhasOnibus;
    }

    public String getBairroInicial() {
        return bairroInicial;
    }

    public void setBairroInicial(String bairroInicial) {
        this.bairroInicial = bairroInicial;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getBairroFinal() {
        return bairroFinal;
    }

    public void setBairroFinal(String bairroFinal) {
        this.bairroFinal = bairroFinal;
    }
}
