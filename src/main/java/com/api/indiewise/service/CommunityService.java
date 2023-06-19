package com.api.indiewise.service;

import com.api.indiewise.models.CommentsModel;
import com.api.indiewise.models.CommunityModel;
import com.api.indiewise.models.PostModel;
import com.api.indiewise.repositories.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {
    @Autowired
    CommunityRepository communityRepository;

    public CommunityModel saveCommunity(CommunityModel communityModel){return communityRepository.save(communityModel);}
    public List<CommunityModel> findAllCommunits(){return communityRepository.findAll();}
    public Optional<CommunityModel> findCommunityById(String id){return communityRepository.findById(id);}
    public void deleteCommunity(String id){communityRepository.deleteById(id);}

}
