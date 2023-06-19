package com.api.indiewise.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.indiewise.models.PostModel;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String>{
    
    boolean existsPostByid(String id);
    void deletePostById(String id);
    List<PostModel> findAllPostByCommunityId(String communityId);
}