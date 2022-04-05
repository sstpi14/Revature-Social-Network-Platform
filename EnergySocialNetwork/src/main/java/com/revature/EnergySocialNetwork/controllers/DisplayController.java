package com.revature.EnergySocialNetwork.controllers;

import com.revature.EnergySocialNetwork.models.Display;
import com.revature.EnergySocialNetwork.models.JsonResponse;
import com.revature.EnergySocialNetwork.services.DisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("display")
public class DisplayController {

    private DisplayService displayService;

    /**
     *
     * @param displayService variable given to reference the DisplayService from services package
     */
    @Autowired
    public DisplayController(DisplayService displayService) {
        this.displayService = displayService;
    }

    /**
     *
     * get all displays (most likely going to use this for home feed) - works
     * @return all the displays from the arraylist
     */
    @GetMapping
    public List<Display> getAllDisplay() {
        return displayService.getAll();
    }

    /**
     *
     * @param profileId users Id given when the profile was made
     * @return get all displays made by one user
     */
    @GetMapping("profile/{profileId}")
    public List<Display> getAllByProfileId(@PathVariable Integer profileId) {
        return displayService.getAllByProfileId(profileId);
    }

    /**
     *
     * @param displayId Id of the displays made within the database
     * @return getting one singular display by displayId
     */
    @GetMapping("display/{displayId}")
    public Display getOneByDisplayId(@PathVariable Integer displayId) {
        return displayService.getOneByDisplayId(displayId);
    }

    /**
     *
     * @param displayId Id of the displays made within the database
     * @return deleting a display by displayId
     */
    @DeleteMapping("delete/{displayId}")
    public Display deleteDisplay(@PathVariable Integer displayId) {
        displayService.deleteDisplay(displayId);
        return null;
    }

    /**
     *
     * @param display variable for when a display is sent to the backend
     * @return creating a display
     */
    @PostMapping
    public Display createDisplay(@RequestBody Display display) {
        return displayService.createDisplay(display);
    }

}
