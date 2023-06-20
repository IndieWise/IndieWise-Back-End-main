package com.api.indiewise.dto;

import jakarta.validation.constraints.NotBlank;

public class CommunityDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    private String imageId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
