package com.revature.EnergySocialNetwork.controllers;

import com.revature.EnergySocialNetwork.models.JsonResponse;
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
    public JsonResponse getOne(@PathVariable Integer profileId){
        JsonResponse jsonResponse;
        Profile profileFromDB = profileService.getOne(profileId);
            if (profileFromDB == null){
                jsonResponse = new JsonResponse(false,"profile with id: "+ profileId + " not found",null);
            }
            else {
                jsonResponse = new JsonResponse(true,"Got profile with id: "+ profileId,profileFromDB);
            }
        return jsonResponse;
    }

    /**
     *
     * @param profile variable of a user
     * @return once information is passed this will create a new user within the database
     */
    @PostMapping
    public JsonResponse createProfile(@RequestBody Profile profile){
        JsonResponse jsonResponse;
        Profile profileFromDB = profileService.createOne(profile);
            if (profileFromDB == null){
                jsonResponse = new JsonResponse(false,"email or username is already in the database",null);
            }
            else {
                jsonResponse = new JsonResponse(true,"Profile has been created",profileFromDB);
            }
        return jsonResponse;
    }

    /**
     *
     * @param profile variable of a user
     * @return once a user modifies/edits their profile this will save and changes the values within the database
     */
    @PatchMapping
    public JsonResponse updateProfile(@RequestBody Profile profile){
        JsonResponse jsonResponse;
        Profile profileFromDB = profileService.updateOne(profile);
            if (profileFromDB == null){
                jsonResponse = new JsonResponse(false,"Profile is not in the database",null);
            }
            else {
                jsonResponse = new JsonResponse(true,"Profile has been updated",profileFromDB);
            }
        return jsonResponse;
    }

    /**
     *
     * @param username variable of the user's username they inputted
     * @return a display of the user's information (username,password,etc.)
     */
    @GetMapping("username/{username}")
    public JsonResponse getOneProfileByUsername(@PathVariable String username){
        JsonResponse jsonResponse;
        Profile profileFromDB = profileService.getOneGivenUsername(username);
            if (profileFromDB == null){
                jsonResponse = new JsonResponse(false,"Profile with "+username+ " username was not found",null);
            }
            else {
                jsonResponse = new JsonResponse(true,"Profile found by username",profileFromDB);
            }
        return jsonResponse;
    }

    /**
     *
     * @return will display all users created in the database
     */
    @GetMapping("all")
    public List<Profile> getAllProfile(){return profileService.getAll();}

}