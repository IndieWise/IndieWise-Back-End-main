package com.api.indiewise.repositories;

import com.api.indiewise.models.CommunityModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends MongoRepository<CommunityModel, String> {

}
