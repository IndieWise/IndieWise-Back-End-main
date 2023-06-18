package com.api.indiewise.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import java.util.Optional;
import org.bson.types.ObjectId;
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
import com.api.indiewise.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/indiewise")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostModel> savePost(@RequestBody @Valid PostDto postDto){
        var postModel = new PostModel();
        BeanUtils.copyProperties(postDto, postModel);
        postModel.setPostDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.savePost(postModel));
    }
    
    @GetMapping("/post")
    public ResponseEntity<List<PostModel>> getAllPost(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAllPost());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity <Object> getOnePost(@PathVariable String postId){
        Optional<PostModel> optionalPostModel = postService.findPostById(postId);
        if(!optionalPostModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(optionalPostModel.get());
    }
    
    @PutMapping("/post/{postId}")
    public ResponseEntity <Object> updatePost(@PathVariable(value = "id") String postId,
                                              @RequestBody @Valid PostDto postDto){
        Optional<PostModel> optionalPostModel = postService.findPostById(postId);
        if(!optionalPostModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        var postModel = new PostModel();
        BeanUtils.copyProperties(postDto, postModel);
        postModel.setId(optionalPostModel.get().getId());
        postModel.setPostDate(optionalPostModel.get().getPostDate());
        return ResponseEntity.status(HttpStatus.OK).body(postService.savePost(postModel));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable String postId){
        Optional<PostModel> optionalPostModel = postService.findPostById(postId);
        if(!optionalPostModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post Não Encontrado");
        }
        postService.deletePostById(postId);
        return ResponseEntity.status(HttpStatus.OK).body("Post deletado");
    }

    // Comments

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<Object> addComment(@PathVariable("postId") String postId,
                                             @RequestBody @Valid CommentsDto commentsDto) {
        Optional<PostModel> optionalPostModel = postService.findPostById(postId);
        if(!optionalPostModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post Não Encontrado");
        }
        var postModel = optionalPostModel.get();
        var commentsModel = new CommentsModel();
        BeanUtils.copyProperties(commentsDto, commentsModel);
        commentsModel.setId(ObjectId.get().toString());
        commentsModel.setCommentdate(LocalDateTime.now(ZoneId.of("UTC")));
        postModel.getComments().add(commentsModel);
        postService.savePost(postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comentários adicionado");
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentsModel>> getAllComments(@PathVariable("postId") String postId) {
        Optional <PostModel> optionalPostModel = postService.findPostById(postId);
        if (!optionalPostModel.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        var postModel = optionalPostModel.get();
        List<CommentsModel> comments = postModel.getComments();
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    // Atualize um comentário específico de um post
    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentsModel> updateComment(@PathVariable("postId") String postId,
                                                       @PathVariable("commentId") String commentId,
                                                       @RequestBody @Valid CommentsDto commentsDto) {

            Optional<PostModel> optionalPostModel = postService.findPostById(postId);
            if (!optionalPostModel.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            var postModel = optionalPostModel.get();
            CommentsModel comment = postModel.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElse(null);

        if (comment == null) {
                return ResponseEntity.notFound().build();
            }
            comment.setTexto(commentsDto.getTexto());
            postService.savePost(postModel);
            return ResponseEntity.ok(comment);
    }

    // Exclua um comentário específico de um post
    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable("postId") String postId,
                                                @PathVariable("commentId") String commentId) {
            Optional<PostModel> optionalPostModel = postService.findPostById(postId);
            if (!optionalPostModel.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            var postModel = optionalPostModel.get();
            CommentsModel comment = postModel.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElse(null);
            if (comment == null) {
                return ResponseEntity.notFound().build();
            }
            postModel.getComments().remove(comment);
            postService.savePost(postModel);
            return ResponseEntity.ok().build();
    }
}