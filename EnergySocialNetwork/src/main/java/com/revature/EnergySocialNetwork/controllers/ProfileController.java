package com.revature.EnergySocialNetwork.controllers;

import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {

    private ProfileService profileService;

    /**
     *
     * @param profileService variable given to reference the ProfileService from services package
     */
    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     *
     * @param profileId users Id given when the profile was made
     * @return check information passed to login a user's profile
     */
    @GetMapping("login/{profileId}")
    public Profile getOne(@PathVariable Integer profileId){
        return profileService.getOne(profileId);
    }

    /**
     *
     * @param profile variable of a user
     * @return once information is passed this will create a new user within the database
     */
    @PostMapping
    public Profile createProfile(@RequestBody Profile profile){
        return profileService.createOne(profile);
    }

    /**
     *
     * @param profile variable of a user
     * @return once a user modifies/edits their profile this will save and changes the values within the database
     */
    @PutMapping
    public Profile updateProfile(@RequestBody Profile profile){
        return profileService.updateOne(profile);
    }

    /**
     *
     * @param username variable of the user's username they inputted
     * @return a display of the user's information (username,password,etc.)
     */
    @GetMapping("username/{username}")
    public Profile getOneProfileByUsername(@PathVariable String username){
        return profileService.getOneGivenUsername(username);
    }

    /**
     *
     * @return will display all users created in the database
     */
    @GetMapping("all")
    public List<Profile> getAllProfile(){
        return profileService.getAll();
    }

}