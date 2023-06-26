package com.api.indiewise.dto;

import com.api.indiewise.models.CommentsModel;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
public class PostDto {
    private String userId;
    private String userName;
    private String texto;
    private LocalDateTime postDate;
    private List<CommentsModel> comments;
    private String communityId;
    private String communityName;
    private String perfilImageId;
    private String imageId;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public List<CommentsModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentsModel> comments) {
        this.comments = comments;
    }

    public String getPerfilImageId() {
        return perfilImageId;
    }

    public void setPerfilImageId(String perfilImageId) {
        this.perfilImageId = perfilImageId;
    }
}
