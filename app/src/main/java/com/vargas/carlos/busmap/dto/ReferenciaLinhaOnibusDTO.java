package com.vargas.carlos.busmap.dto;

public class ReferenciaLinhaOnibusDTO {

    public int id;
    public int id_linhas_onibus;
    public String horarios;
    public String sentido;
    public String legendas;
    public String encerramento;
    public TrajetoReferenciaLinhaOnibusDTO[] trajetos;

    public ReferenciaLinhaOnibusDTO(){}

    public ReferenciaLinhaOnibusDTO(int id, int id_linhas_onibus, String horarios, String sentido, String legendas, String encerramento, TrajetoReferenciaLinhaOnibusDTO[] trajetos) {
        this.id = id;
        this.id_linhas_onibus = id_linhas_onibus;
        this.horarios = horarios;
        this.sentido = sentido;
        this.legendas = legendas;
        this.encerramento = encerramento;
        this.trajetos = trajetos;
    }
}
