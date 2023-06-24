package com.api.indiewise.controllers;

import com.api.indiewise.dto.CommunityDto;
import com.api.indiewise.models.CommunityModel;
import com.api.indiewise.service.CommunityService;
import com.api.indiewise.service.ImageService;
import com.api.indiewise.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/indiewise")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CommunityController {
    @Autowired
    CommunityService communityService;

    @Autowired
    ImageService imageService;

    @PostMapping("/community")
    public ResponseEntity <CommunityModel> saveCommunity(@RequestBody @Valid CommunityDto communityDto){
        var commnunityModel = new CommunityModel();
        BeanUtils.copyProperties(communityDto, commnunityModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(communityService.saveCommunity(commnunityModel));
    }

    @GetMapping("/community")
    public ResponseEntity <List<CommunityModel>> getAllCommunitys(){
        return ResponseEntity.status(HttpStatus.OK).body(communityService.findAllCommunits());
    }

    @GetMapping("/community/{communityId}")
    public ResponseEntity <Object> getOneCommunity(@PathVariable String communityId){
        Optional <CommunityModel> optionalCommunityModel = communityService.findCommunityById(communityId);
        if(!optionalCommunityModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunidade não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalCommunityModel.get());
    }

    @PutMapping("/community/{communityId}")
    public ResponseEntity <Object> updateCommunity(@PathVariable String communityId,
                                                   @RequestBody @Valid CommunityDto communityDto){
        Optional <CommunityModel> optionalCommunityModel = communityService.findCommunityById(communityId);
        if(!optionalCommunityModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunidade não encontrada");
        }
        var communityModel = new CommunityModel();
        BeanUtils.copyProperties(communityDto, communityModel);
        communityModel.setId(optionalCommunityModel.get().getId());
        communityModel.setMembers(optionalCommunityModel.get().getMembers());
        communityModel.setUserId(optionalCommunityModel.get().getUserId());
        communityModel.setUserName(optionalCommunityModel.get().getUserName());
        return ResponseEntity.status(HttpStatus.OK).body(communityService.saveCommunity(communityModel));
    }

    @DeleteMapping("/community/{communityId}")
    public ResponseEntity<Object> deleteCommunity(@PathVariable String communityId){
        Optional <CommunityModel> optionalCommunityModel = communityService.findCommunityById(communityId);
        if(!optionalCommunityModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunidade não encontrada");
        }
        if(optionalCommunityModel.get().getImageId() != null){
            imageService.deleteImage(optionalCommunityModel.get().getImageId());
        }
        communityService.deleteCommunity(communityId);
        return ResponseEntity.status(HttpStatus.OK).body("Comunidade Deletada");
    }


    //Controle de usuários dentro da plataforma


//    @PostMapping("/community/{communityId}/{userId}")
//    public ResponseEntity<Object> addMembers(@PathVariable String communityId, @PathVariable String userId){
//        Optional <CommunityModel> optionalCommunityModel = communityService.findCommunityById(communityId);
//        if(!optionalCommunityModel.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comunidade não encontrada");
//        }
//
//    }

}
