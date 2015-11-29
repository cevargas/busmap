package com.vargas.carlos.busmap.model;

public class ReferenciaLinhasOnibus {

    private int id;
    private LinhasOnibus idLinhasOnibus;
    private String horarios;
    private String sentido;
    private String legendas;
    private String encerramento;

    public ReferenciaLinhasOnibus(int id, LinhasOnibus idLinhasOnibus, String horarios, String sentido, String legendas, String encerramento) {
        this.id = id;
        this.idLinhasOnibus = idLinhasOnibus;
        this.horarios = horarios;
        this.sentido = sentido;
        this.legendas = legendas;
        this.encerramento = encerramento;
    }

    public ReferenciaLinhasOnibus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinhasOnibus getIdLinhasOnibus() {
        return idLinhasOnibus;
    }

    public void setIdLinhasOnibus(LinhasOnibus idLinhasOnibus) {
        this.idLinhasOnibus = idLinhasOnibus;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public String getLegendas() {
        return legendas;
    }

    public void setLegendas(String legendas) {
        this.legendas = legendas;
    }

    public String getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(String encerramento) {
        this.encerramento = encerramento;
    }
}
