package com.api.indiewise.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
public class PostDto {
    @NotBlank
    private String texto;
    private List<CommentsDto> comentarios;
    private String communityId;
    private String imageId;
    private String communityName;

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

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
