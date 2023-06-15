package com.api.indiewise.controllers;
//teste 2 15/06
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.indiewise.dto.CommentsDto;
import com.api.indiewise.dto.PostDto;
import com.api.indiewise.models.CommentsModel;
import com.api.indiewise.models.PostModel;
import com.api.indiewise.service.IndieWiseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/indiewise")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class IndieWiseController {
    @Autowired
    IndieWiseService indieWiseService;

    @PostMapping("/post")
    public ResponseEntity<PostModel> savePost(@RequestBody @Valid PostDto postDto){
        var postModel = new PostModel();
        BeanUtils.copyProperties(postDto, postModel);
        postModel.setPostDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(indieWiseService.savePost(postModel));
    }
    
    @GetMapping("/post")
    public ResponseEntity<List<PostModel>> getAllPost(){
        return ResponseEntity.status(HttpStatus.OK).body(indieWiseService.findAllPost());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity <Object> getOnePost(@PathVariable String postId){
        Optional<PostModel> optionalPostModel = indieWiseService.findPostById(postId);
        if(!optionalPostModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(optionalPostModel.get());
    }
    
    @PutMapping("/post/{postId}")
    public ResponseEntity <Object> updatePost(@PathVariable(value = "id") String postId, @RequestBody @Valid PostDto postDto){
        Optional<PostModel> optionalPostModel = indieWiseService.findPostById(postId);
        if(!optionalPostModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        var postModel = new PostModel();
        BeanUtils.copyProperties(postDto, postModel);
        postModel.setId(optionalPostModel.get().getId());
        postModel.setPostDate(optionalPostModel.get().getPostDate());
        return ResponseEntity.status(HttpStatus.OK).body(indieWiseService.savePost(postModel));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable String postId){
        Optional<PostModel> optionalPostModel = indieWiseService.findPostById(postId);
        if(!optionalPostModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post Não Encontrado");
        }
        indieWiseService.deletePostById(postId);
        return ResponseEntity.status(HttpStatus.OK).body("Post deletado");
    }
    // Comments
    @PutMapping("/{postId}/comments")
    public ResponseEntity<Object> saveComment(@PathVariable String postId, @RequestBody CommentsDto commentsDto){
        var commentsModel = new CommentsModel();
        BeanUtils.copyProperties(commentsDto, commentsModel);
        commentsModel.setCommentdata(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(indieWiseService.saveComment(commentsModel));
    }
}