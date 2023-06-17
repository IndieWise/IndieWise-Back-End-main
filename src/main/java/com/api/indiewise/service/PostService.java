package com.api.indiewise.service;

import java.util.List;
import java.util.Optional;

import com.api.indiewise.models.CommentsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.indiewise.models.PostModel;
import com.api.indiewise.models.CommentsModel;
import com.api.indiewise.repositories.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public PostModel savePost(PostModel postModel){
        return postRepository.save(postModel);
    }
    public boolean existsByid(String id){
        return postRepository.existsById(id);
    }
    public Optional<PostModel> findPostById(String id){
        return postRepository.findById(id);
    }
    public List<PostModel> findAllPost(){
        return postRepository.findAll();
    }
    public void deletePostById(String id){
        postRepository.deleteById(id);
    }
    //Comments

}

