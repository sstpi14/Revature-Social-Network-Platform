package com.revature.EnergySocialNetwork.controllers;

import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("Login/{profileId}")
    public Profile getOne(@PathVariable Integer profileId){
        return profileService.getOne(profileId);
    }

    @PostMapping("Register")
    public Profile createProfile(@RequestBody Profile profile){
        return profileService.createOne(profile);
    }

    @PutMapping
    public Profile updateProfile(@RequestBody Profile profile){
        return profileService.updateOne(profile);
    }

    @GetMapping("{username}")
    public Profile getOneProfileByUsername(@PathVariable String username){
        return profileService.getOneGivenUsername(username);
    }

    @GetMapping
    public List<Profile> getAllProfile(){
        return profileService.getAll();
    }
}