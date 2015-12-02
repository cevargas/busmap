package com.vargas.carlos.busmap.dto;

public class HorariosTrajetoReferenciaLinhaOnibusDTO {

    public int id;
    public int id_trajetos_ref_linhas_onibus;
    public String inicializador;
    public String centralizador;
    public String finalizador;

    public HorariosTrajetoReferenciaLinhaOnibusDTO() {
    }

    public HorariosTrajetoReferenciaLinhaOnibusDTO(int id, int id_trajetos_ref_linhas_onibus, String inicializador, String centralizador, String finalizador) {
        this.id = id;
        this.id_trajetos_ref_linhas_onibus = id_trajetos_ref_linhas_onibus;
        this.inicializador = inicializador;
        this.centralizador = centralizador;
        this.finalizador = finalizador;
    }
}
