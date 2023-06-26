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
    private String userId;
    private String userName;
    private String texto;
    private LocalDateTime postDate;
    private List<CommentsModel> comments;
    private String communityId;
    private String communityName;
    private String perfilImageId;
    private String imageId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPerfilImageId() {
        return perfilImageId;
    }

    public void setPerfilImageId(String perfilImageId) {
        this.perfilImageId = perfilImageId;
    }
}
