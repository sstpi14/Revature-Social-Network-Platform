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
    @GetMapping("profile/{profileId}") //checked
    public JsonResponse getAllByProfileId(@PathVariable Integer profileId) {
        JsonResponse jsonResponse;
        List<Display> displaysFromDb = displayService.getAllByProfileId(profileId);
            if (displaysFromDb == null){
                jsonResponse = new JsonResponse(false, "No displays match this profileId", null);
            } else {
                jsonResponse = new JsonResponse(true, "Displaying all displays from ProfileId: " + profileId, displaysFromDb);
            }

        return jsonResponse;
    }

    /**
     *
     * @param displayId Id of the displays made within the database
     * @return getting one singular display by displayId
     */
    @GetMapping("display/{displayId}") //checked
    public JsonResponse getOneByDisplayId(@PathVariable Integer displayId) {
        JsonResponse jsonResponse;
        Display displayFromDb = displayService.getOneByDisplayId(displayId);
            if (displayFromDb == null){
                jsonResponse = new JsonResponse(false, "There are no displays matching this displayId", null);
            } else {
                jsonResponse = new JsonResponse(true, "Displaying display with displayId: " + displayId, displayFromDb);
            }
        return jsonResponse;
    }

    /**
     *
     * @param displayId Id of the displays made within the database
     * @return deleting a display by displayId
     */
    @DeleteMapping("delete/{displayId}") //checked
    public JsonResponse deleteDisplay(@PathVariable Integer displayId) {
        JsonResponse jsonResponse;
        Display displayFromDb = displayService.getOneByDisplayId(displayId);
            if (displayFromDb == null) {
                jsonResponse = new JsonResponse(false, "There are no displays matching this displayId", null);
            } else {
                jsonResponse = new JsonResponse(true, "Display with displayId " + displayId + " has been deleted.", null);
                displayService.deleteDisplay(displayId);
            }

        return jsonResponse;
    }

    /**
     *
     * @param display variable for when a display is sent to the backend
     * @return creating a display
     */
    @PostMapping //checked
    public JsonResponse createDisplay(@RequestBody Display display) {
        JsonResponse jsonResponse;
        Display displayFromDb = displayService.createDisplay(display);
            if (displayFromDb == null) {
                jsonResponse = new JsonResponse(false, "Display needs to include image or description", null);
            } else {
                jsonResponse = new JsonResponse(true, "Display has been created", displayFromDb);
            }
        return jsonResponse;
    }

    @PatchMapping("{displayId}/profile/{profileId}") //checked
    public JsonResponse addLikeToDisplay(@PathVariable Integer profileId,@PathVariable Integer displayId){
        JsonResponse jsonResponse;
        Display displayFromDB = displayService.addToLike(profileId,displayId);
            if (displayFromDB == null){
                jsonResponse = new JsonResponse(false, "Profile has already liked this post", null);
            } else {
                jsonResponse = new JsonResponse(true, "Profile with profileId: " + profileId + "has liked Display with displayId: " + displayId, displayFromDB);
            }
        return jsonResponse;
    }

    @GetMapping("likers/{displayId}")
    public List<Profile> getAllLikersForOneDisplay(@PathVariable Integer displayId){
        return displayService.getAllLikers(displayId);
    }
}
