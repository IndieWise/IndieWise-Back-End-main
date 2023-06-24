package com.api.indiewise.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentsDto {
    @NotBlank
    private String texto;
    private String userId;
    private String userName;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
