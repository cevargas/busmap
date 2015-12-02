package com.vargas.carlos.busmap.model;

public class HorarioTrajetoReferenciaLinhasOnibus {

    private int id;
    private TrajetoReferenciaLinhasOnibus trajetoReferenciaLinhasOnibus;
    private String inicializador;
    private String centralizado;
    private String finalizacao;

    public HorarioTrajetoReferenciaLinhasOnibus(int id, TrajetoReferenciaLinhasOnibus trajetoReferenciaLinhasOnibus, String inicializador, String centralizado, String finalizacao) {
        this.id = id;
        this.trajetoReferenciaLinhasOnibus = trajetoReferenciaLinhasOnibus;
        this.inicializador = inicializador;
        this.centralizado = centralizado;
        this.finalizacao = finalizacao;
    }

    public HorarioTrajetoReferenciaLinhasOnibus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrajetoReferenciaLinhasOnibus getTrajetoReferenciaLinhasOnibus() {
        return trajetoReferenciaLinhasOnibus;
    }

    public void setTrajetoReferenciaLinhasOnibus(TrajetoReferenciaLinhasOnibus trajetoReferenciaLinhasOnibus) {
        this.trajetoReferenciaLinhasOnibus = trajetoReferenciaLinhasOnibus;
    }

    public String getInicializador() {
        return inicializador;
    }

    public void setInicializador(String inicializador) {
        this.inicializador = inicializador;
    }

    public String getCentralizado() {
        return centralizado;
    }

    public void setCentralizado(String centralizado) {
        this.centralizado = centralizado;
    }

    public String getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(String finalizacao) {
        this.finalizacao = finalizacao;
    }
}
