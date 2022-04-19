package com.revature.EnergySocialNetwork.controllers;

import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "session")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class SessionController {

    private ProfileService profileService;

    /**
     *
     * @param profileService variable given to reference the ProfileService from services package
     */
    @Autowired
    public SessionController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     *
     * @param httpSession the url session that will be the frontend
     * @param profile variable of a user
     * @return allows a user to login and access the website
     */
    @PostMapping
    public ResponseEntity<Profile> login(HttpSession httpSession, @RequestBody Profile profile){
        httpSession.setAttribute("profile", profile);
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

    /**
     *
     * @param httpSession the url session that will be the frontend
     * @return a string that the user has log out and can not be accessed until they log back in
     */
    @DeleteMapping
    public ResponseEntity<String> logout(HttpSession httpSession){
        httpSession.setAttribute("profile",null);
        return ResponseEntity.status(HttpStatus.OK).body("Logged out and session invalidated");
    }

    /**
     *
     * @param httpSession the url session that will be the frontend
     * @return verifies that the session can not be accessed by various methods other than logging in
     */
    @GetMapping
    public ResponseEntity<Profile> checkSession(HttpSession httpSession){
        Profile profile = (Profile) httpSession.getAttribute("profile");

        if (profile == null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }
}

