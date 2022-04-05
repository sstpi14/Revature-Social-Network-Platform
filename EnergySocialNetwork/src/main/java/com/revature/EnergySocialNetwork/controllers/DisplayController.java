package com.revature.EnergySocialNetwork.controllers;

import com.revature.EnergySocialNetwork.models.Display;
import com.revature.EnergySocialNetwork.models.JsonResponse;
import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.services.DisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("display")
public class DisplayController {

    private DisplayService displayService;

    @Autowired
    public DisplayController(DisplayService displayService) {
        this.displayService = displayService;
    }

    //get all displays (most likely going to use this for home feed) - works
    @GetMapping
    public List<Display> getAllDisplay() {
        return displayService.getAll();
    }

    //get all displays made by one user - works
    @GetMapping("profile/{profileId}")
    public List<Display> getAllByProfileId(@PathVariable Integer profileId) {
        return displayService.getAllByProfileId(profileId);
    }

    //getting one singular display by displayId - works
    @GetMapping("display/{displayId}")
    public Display getOneByDisplayId(@PathVariable Integer displayId) {
        return displayService.getOneByDisplayId(displayId);
    }

    //deleting a display by displayId - works
    @DeleteMapping("delete/{displayId}")
    public Display deleteDisplay(@PathVariable Integer displayId) {
        displayService.deleteDisplay(displayId);
        return null;
    }

    //creating a display - works
    @PostMapping
    public Display createDisplay(@RequestBody Display display) {
        return displayService.createDisplay(display);
    }

    @PatchMapping("{displayId}/profile/{profileId}")
    public Display addLikeToDisplay(@PathVariable Integer profileId,@PathVariable Integer displayId){
        Display displayFromDB = displayService.addToLike(profileId,displayId);
        if (displayFromDB != null){
            return displayFromDB;
        }
        return null;
    }

    @GetMapping("likers/{displayId}")
    public List<Profile> getAllLikersForOneDisplay(@PathVariable Integer displayId){
        return displayService.getAllLikers(displayId);
    }
}
