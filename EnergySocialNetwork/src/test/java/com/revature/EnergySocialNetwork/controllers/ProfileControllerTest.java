package com.revature.EnergySocialNetwork.controllers;

import com.revature.EnergySocialNetwork.models.JsonResponse;
import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.repositories.ProfileDAO;
import com.revature.EnergySocialNetwork.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileControllerTest {
    private ProfileController profileController;

    private ProfileService profileService = Mockito.mock(ProfileService.class);

    public ProfileControllerTest() {
        this.profileController = new ProfileController(profileService);
    }

    Profile profile = new Profile(1,"test","test123","tester","testing",null,"test@email.com");
    JsonResponse jsonResponse;

    @Test
    void getOne() {
        //arrange
        List<Profile> db = new ArrayList<>();
        db.add(profile);
        JsonResponse expectedOutput = new JsonResponse(true,"Got profile with id: "+ profile.getProfileId(),profile);
        Mockito.when(profileService.getOne(profile.getProfileId())).thenReturn(profile);
        //act
        JsonResponse actualOutput = profileController.getOne(profile.getProfileId());
        //assert
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void getOneFail() {
        //arrange
        List<Profile> db = new ArrayList<>();
        JsonResponse expectedOutput = new JsonResponse(false,"profile with id: "+ profile.getProfileId() + " not found" ,null);
        Mockito.when(profileService.getOne(profile.getProfileId())).thenReturn(null);
        //act
        JsonResponse actualOutput = profileController.getOne(profile.getProfileId());
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void createProfile() {
        //arrange
        List<Profile> db = new ArrayList<>();
        JsonResponse expectedOutput = new JsonResponse(true,"Profile has been created",profile);
        Mockito.when(profileService.createOne(profile)).thenReturn(profile);

        //act
        JsonResponse actualOutput = profileController.createProfile(profile);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void createProfileFail() {
        //arrange
        List<Profile> db = new ArrayList<>();
        db.add(profile);
        JsonResponse expectedOutput = new JsonResponse(false,"email or username is already in the database",null);
        Mockito.when(profileService.createOne(profile)).thenReturn(null);

        //act
        JsonResponse actualOutput = profileController.createProfile(profile);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void updateProfile() {
        //arrange
        List<Profile> db = new ArrayList<>();
        db.add(profile);
        JsonResponse expectedOutput = new JsonResponse(true,"Profile has been updated",profile);
        Mockito.when(profileService.updateOne(profile)).thenReturn(profile);
        //act
        JsonResponse actualOutput = profileController.updateProfile(profile);
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void updateProfileFail() {
        //arrange
        List<Profile> db = new ArrayList<>();
        JsonResponse expectedOutput = new JsonResponse(false,"Profile is not in the database",null);
        Mockito.when(profileService.updateOne(profile)).thenReturn(null);
        //act
        JsonResponse actualOutput = profileController.updateProfile(profile);
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getOneProfileByUsername() {
        //arrange
        JsonResponse expectedOutput = new JsonResponse(true,"Profile found by username",profile);
        Mockito.when(profileService.getOneGivenUsername(profile.getUsername())).thenReturn(profile);
        //act
        JsonResponse actualOutput = profileController.getOneProfileByUsername(profile.getUsername());
        //assert
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    void getOneProfileByUsernameFail() {
        //arrange
        List<Profile> db = new ArrayList<>();
        JsonResponse expectedOutput = new JsonResponse(false, "Profile with "+profile.getUsername()+ " username was not found",null);
        Mockito.when(profileService.getOneGivenUsername(profile.getUsername())).thenReturn(null);
        //act
        JsonResponse actualOutput = profileController.getOneProfileByUsername(profile.getUsername());
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAllProfile() {

    }

}