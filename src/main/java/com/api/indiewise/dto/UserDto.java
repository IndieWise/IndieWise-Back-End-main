package com.api.indiewise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotBlank
    @Size(min = 6, max = 30)
    private String username;
    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min = 6)
    private String confirmPassword;

    private String imageId;

    private String conhecimento;

    private String comoAdquiriu;

    private String precoAula;

    private String tituloPerfil;

    private String descricaoPerfil;


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(String conhecimento) {
        this.conhecimento = conhecimento;
    }

    public String getComoAdquiriu() {
        return comoAdquiriu;
    }

    public void setComoAdquiriu(String comoAdquiriu) {
        this.comoAdquiriu = comoAdquiriu;
    }

    public String getPrecoAula() {
        return precoAula;
    }

    public void setPrecoAula(String precoAula) {
        this.precoAula = precoAula;
    }

    public String getTituloPerfil() {
        return tituloPerfil;
    }

    public void setTituloPerfil(String tituloPerfil) {
        this.tituloPerfil = tituloPerfil;
    }

    public String getDescricaoPerfil() {
        return descricaoPerfil;
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }
}
