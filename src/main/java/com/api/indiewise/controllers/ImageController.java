package com.api.indiewise.controllers;

import com.api.indiewise.models.ImageModel;
import com.api.indiewise.service.ImageService;
import jakarta.activation.MimetypesFileTypeMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/indiewise")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("/image")
    public ResponseEntity<String> saveImage(@RequestParam("file") MultipartFile file){
        try {
            ImageModel imageModel = new ImageModel();
            imageModel.setContent(file.getBytes());
            ImageModel savedImage = imageService.saveImage(imageModel);
            return ResponseEntity.ok().body(savedImage.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fazer upload da imagem");
        }
    }


    @GetMapping("/image/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageId) {
        Optional<ImageModel> optionalImageModel = imageService.findImageById(imageId);
        if (!optionalImageModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrada".getBytes());
        }

        ImageModel imageModel = optionalImageModel.get();
        byte[] imageContent = imageModel.getContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(imageContent.length);

        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }



}
