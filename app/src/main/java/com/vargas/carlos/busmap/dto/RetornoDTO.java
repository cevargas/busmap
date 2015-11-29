package com.vargas.carlos.busmap.dto;

import java.util.List;

public class RetornoDTO {

    public List<LinhaOnibusDTO> linhas_onibus;

    public RetornoDTO() {
    }

    public RetornoDTO(List<LinhaOnibusDTO> linhas_onibus) {
        this.linhas_onibus = linhas_onibus;
    }
}
