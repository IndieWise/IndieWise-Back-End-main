package com.api.indiewise.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentsDto {
    @NotBlank
    private String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
