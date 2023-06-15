package com.api.indiewise.models;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection =  "post")
public class PostModel {
     
    @Id
    private String id;
    private String texto;
    private LocalDateTime postDate;
    
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
    public LocalDateTime getPostDate() {
        return postDate;
    }
    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }
    
}
