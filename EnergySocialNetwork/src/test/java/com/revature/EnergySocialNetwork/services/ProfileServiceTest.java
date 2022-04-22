package com.revature.EnergySocialNetwork.services;

import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.repositories.ProfileDAO;
import com.revature.EnergySocialNetwork.repositories.ProfileDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


class ProfileServiceTest {
    private ProfileService profileService;

    private ProfileDAO profileDAO = Mockito.mock(ProfileDAO.class);

    public ProfileServiceTest(){
        this.profileService = new ProfileService(profileDAO);
    }

    Profile profile = new Profile(1,"test","test123","tester","testing",null,"test@email.com");

    @Test
    void createOne() {
        //arrange
        Profile expectedOutput = profile;
        Profile profile = new Profile(1,"test","test123","tester","testing","test@email.com");
        Profile profile1 = new Profile(2, "test2", "pass", "test", "test", "test@test.com");
        Profile fromDB = profile;
        Integer profileId = profile.getProfileId();
        String username = profile.getUsername();
        String email = profile.getEmail();
        //methods to be used during act
        Mockito.when(profileDAO.createProfile(profile)).thenReturn(profileId);
        Mockito.when(profileDAO.getOne(profileId)).thenReturn(profile);
        //act
        Profile actualOutput = profileService.createOne(profile);
        //assert
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void getOne() {

        //arrange
        int profileId = profile.getProfileId();
        Profile expectedOutput = profile;
        List<Profile> outputs = new ArrayList<>();
        outputs.add(profile);
        Mockito.when(profileDAO.getAll()).thenReturn(outputs);
        Mockito.when(profileDAO.getOne(profileId)).thenReturn(expectedOutput);
        //act
        Profile actualOutput = profileService.getOne(profileId);
        //assert
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void updateOne() {
       //arrange -only need profile
        Profile expectedOutput = profile;
        //act - call service
        profileService.updateOne(profile);
        //assert - verify it happened
        Mockito.verify(profileDAO).updateProfile(expectedOutput);
    }

    @Test
    void getAll() {
        //arrange
        Profile profile2 = new Profile(2,"test2","test123","tester2","testing2",null,"test2@email.com");
        //what to expect
        List<Profile> expectedOutput = new ArrayList<>();
        //add profiles
        expectedOutput.add(profile);
        expectedOutput.add(profile2);
        Mockito.when(profileDAO.getAll()).thenReturn(expectedOutput);
        //act
        List<Profile> actualOutput = profileService.getAll();

        //assert
        assertEquals(actualOutput,expectedOutput);
    }

    @Test
    void getOneGivenUsername() {
        //arrange
        //set up profiles
        Profile profile = new Profile(1,"test","test123","tester","testing",null,"test@email.com");
        Profile profile2 = new Profile(2,"test2","test123","tester2","testing2",null,"test2@email.com");
        //what to expect
        Profile expectedOutput = profile;
        //location of expectation
        List<Profile> profileCheck = new ArrayList<>();
        //add profiles
        profileCheck.add(profile);
        profileCheck.add(profile2);
        //to be used during act:
            //input
        String username = profile.getUsername();
            //method to get one
        Mockito.when(profileDAO.getOneByUsername(profile.getUsername())).thenReturn(expectedOutput);
            //method to get location
        Mockito.when(profileDAO.getAll()).thenReturn(profileCheck);
        //act - call method
        Profile actualOutput = profileService.getOneGivenUsername(username);
        //assert - compare
        assertEquals(expectedOutput,actualOutput);
    }
}