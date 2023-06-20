package com.api.indiewise.service;

import com.api.indiewise.models.ImageModel;
import com.api.indiewise.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;


    public ImageModel saveImage(ImageModel imageModel) {
        return imageRepository.save(imageModel);
    }

    public Optional<ImageModel> findImageById(String id) {
        return imageRepository.findById(id);
    }
}
