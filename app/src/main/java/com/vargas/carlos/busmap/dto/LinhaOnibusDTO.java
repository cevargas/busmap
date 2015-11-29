package com.vargas.carlos.busmap.dto;

public class LinhaOnibusDTO {

    public int id;
    public String nome;
    public String trajeto;
    public String mapa;
    public ReferenciaLinhaOnibusDTO[] referencias;

    public LinhaOnibusDTO() {
    }

    public LinhaOnibusDTO(int id, String nome, String trajeto, String mapa, ReferenciaLinhaOnibusDTO[] referencias) {
        this.id = id;
        this.nome = nome;
        this.trajeto = trajeto;
        this.mapa = mapa;
        this.referencias = referencias;
    }
}
