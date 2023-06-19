package com.api.indiewise.repositories;

import com.api.indiewise.models.ImageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<ImageModel, String> {

}
