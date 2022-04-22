package com.revature.EnergySocialNetwork.services;

import com.revature.EnergySocialNetwork.models.Display;
import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.repositories.DisplayDAO;
import com.revature.EnergySocialNetwork.repositories.ProfileDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayServiceTest {
    private DisplayService displayService;

    private DisplayDAO displayDAO = Mockito.mock(DisplayDAO.class);
    private ProfileDAO profileDAO = Mockito.mock(ProfileDAO.class);

    public DisplayServiceTest(){
        this.displayService = new DisplayService(displayDAO, profileDAO);
    }

    Profile profile = new Profile(1,"dcf", "pass", "Neicey", "Fontenot", null, "dcf@email.com");
    Display display = new Display(1, null, null, null, "test");


    @Test
    void createDisplay() {
        //arrange
            //what i want is a display that has a profile
        Display expectedOutput = new Display(1, null, profile, null, "test");
        //what i need:
            //db list of displays
        List<Display> dbDisplays = new ArrayList<>();
            //in the db is display without profile
        dbDisplays.add(display);
        display.setProfile(profile);
            //mock methods
        Mockito.when(displayDAO.createDisplay(display)).thenReturn(display.getDisplayId());
        Mockito.when(displayDAO.getOne(display.getDisplayId())).thenReturn(display);
        Mockito.when(profileDAO.getOne(display.getProfile().getProfileId())).thenReturn(profile);
        //act
        Display actualOutput = displayService.createDisplay(display);
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAll() {
        //arrange
        List<Display> expectedOutput = new ArrayList<>();
        expectedOutput.add(display);

        Mockito.when(displayDAO.getAll()).thenReturn(expectedOutput);
        //act
        List<Display> actualOutput = displayService.getAll();
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getOneByDisplayId() {
        //arrange
         Display expectedOutput = display;
         Integer displayId = display.getDisplayId();
         Mockito.when(displayDAO.getOne(displayId)).thenReturn(expectedOutput);
        //act
        Display actualOutput = displayService.getOneByDisplayId(displayId);
        //assert
        assertEquals(expectedOutput,actualOutput);
       // return displayService.getOneByDisplayId(displayId);
    }

    @Test
    void getAllByProfileId() {
        //arrange
        Display display = new Display(1, null, profile, null, "test");
        Display display2 = new Display(2, null, profile, null, "test");
        List<Display> expectedOutput = new ArrayList<>();
        expectedOutput.add(display);
        expectedOutput.add(display2);
        List<Profile> dbprofiles = new ArrayList<>();
        dbprofiles.add(profile);

        Mockito.when(profileDAO.getOne(profile.getProfileId())).thenReturn(profile);
        Mockito.when(displayDAO.getAllByProfileId(profile.getProfileId())).thenReturn(expectedOutput);
        //act
        List<Display> actualOutput = displayService.getAllByProfileId(profile.getProfileId());
        //assert
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void deleteDisplay() {
        //arrange
        int displayId = display.getDisplayId();
        //act
        displayService.deleteDisplay(displayId);
        //assert
        Mockito.verify(displayDAO).deleteDisplay(displayDAO.getOne(displayId));
    }

    @Test
    void addToLike() {
        //arrange
        Profile profile = new Profile(1,"dcf", "pass", "Neicey", "Fontenot", null, "dcf@email.com");
        Profile profile2 = new Profile(2,"dcf", "pass", "Neicey", "Fontenot", null, "dcf2@email.com");
        List<Profile> profileThatHaveLiked = new ArrayList<>();
        profileThatHaveLiked.add(profile2);
        Display expectedOutput = new Display(1, null, profile, profileThatHaveLiked, "test");
        profileThatHaveLiked = display.getLikers();

        display.setLikers(profileThatHaveLiked);
        Mockito.when(displayDAO.getOne(display.getDisplayId())).thenReturn(expectedOutput);
        Mockito.when(profileDAO.getOne(profile.getProfileId())).thenReturn(profile);


        //act
        Display actualOutput = displayService.addToLike(profile.getProfileId(),display.getDisplayId());
        //assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAllLikers() {
        //arrange
        Display display = new Display(1, null, null, null, "test");
        List<Profile> likers = new ArrayList<>();
        likers.add(profile);
        List<Profile> expectedOutput = display.getLikers();

        Mockito.when(displayDAO.getOne(display.getDisplayId())).thenReturn(display);
        //act
        List<Profile> actualOutput = displayService.getAllLikers(display.getDisplayId());
        //assert
        assertEquals(expectedOutput, actualOutput);
    }
}