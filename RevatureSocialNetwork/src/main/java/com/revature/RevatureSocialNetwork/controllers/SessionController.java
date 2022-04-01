package com.revature.RevatureSocialNetwork.controllers;

import com.revature.RevatureSocialNetwork.models.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "session")
public class SessionController {

    @PostMapping
    public ResponseEntity<Profile> login(HttpSession httpSession, @RequestBody Profile profile){
        httpSession.setAttribute("profile", profile);
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

    @DeleteMapping
    public ResponseEntity<String> logout(HttpSession httpSession){
        httpSession.setAttribute("profile",null);
        return ResponseEntity.status(HttpStatus.OK).body("Logged out and session invalidated");
    }

    @GetMapping
    public ResponseEntity<Profile> checkSession(HttpSession httpSession){
        Profile profile = (Profile) httpSession.getAttribute("profile");

        if (profile == null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }
}
