package com.api.indiewise.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.indiewise.models.CommentsModel;

@Repository
public interface CommentsRepository extends MongoRepository<CommentsModel, String>{
    boolean existsCommentById(String id);
    void deleteCommentById(String id);
}
