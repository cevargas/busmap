package com.vargas.carlos.busmap.dto;

public class TrajetoReferenciaLinhaOnibusDTO {

    public int id;
    public int id_ref_linhas_onibus;
    public String bairro_inicial;
    public String centro;
    public String bairro_final;

    public TrajetoReferenciaLinhaOnibusDTO() {
    }

    public TrajetoReferenciaLinhaOnibusDTO(int id, int id_ref_linhas_onibus, String bairro_inicial, String centro, String bairro_final) {
        this.id = id;
        this.id_ref_linhas_onibus = id_ref_linhas_onibus;
        this.bairro_inicial = bairro_inicial;
        this.centro = centro;
        this.bairro_final = bairro_final;
    }
}
