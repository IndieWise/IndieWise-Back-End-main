package com.api.indiewise.models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection =  "post")
public class PostModel {
     
    @Id
    private String id;
    private String texto;
    private LocalDateTime postDate;
    private List<CommentsModel> comments;
    private String communityId;

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
    public List<CommentsModel> getComments() {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        return comments;
    }
    public void setComments(List<CommentsModel> comments) {
        this.comments = comments;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
}
