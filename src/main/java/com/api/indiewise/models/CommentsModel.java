package com.api.indiewise.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class CommentsModel {
    @Id
    private String id;
    private String texto;
    private LocalDateTime Commentdate;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public LocalDateTime getCommentdate() {
        return Commentdate;
    }
    public void setCommentdate(LocalDateTime commentdata) {
        Commentdate = commentdata;
    }
    
}
