package com.api.indiewise.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
public class PostDto {
    @NotBlank
    private String texto;
    private List<CommentsDto> comentarios;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<CommentsDto> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<CommentsDto> comentarios) {
        this.comentarios = comentarios;
    }
}
