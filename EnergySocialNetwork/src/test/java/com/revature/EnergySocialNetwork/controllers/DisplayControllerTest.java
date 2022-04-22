package com.revature.EnergySocialNetwork.controllers;

import com.revature.EnergySocialNetwork.models.Display;
import com.revature.EnergySocialNetwork.models.JsonResponse;
import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.services.DisplayService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayControllerTest {



    DisplayController displayController;
    JsonResponse jsonResponse;
    DisplayService displayService = Mockito.mock(DisplayService.class);

    public DisplayControllerTest(){
        this.displayController = new DisplayController(displayService);
    }

    Profile profile = new Profile(1,"dcf", "pass", "Neicey", "Fontenot", null, "dcf@email.com");
    Display display = new Display(1, null, null, null, "test");

    @Test
    void getAllDisplay() {
        //Arrange
        List<Display> expectedOutput = new ArrayList<>();
        Mockito.when(displayService.getAll()).thenReturn(expectedOutput);

        //Act
        List<Display> actualOutput = displayController.getAllDisplay();

        //Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAllByProfileId() {

        //Arrange
        List<Display> displayFromdb= new ArrayList<>();
        Display display = new Display(1, null, profile, null, "test");
        displayFromdb.add(display);
        JsonResponse expectedOutput = new JsonResponse(true, "Displaying all displays from ProfileId: " + profile.getProfileId(), displayFromdb);
        Mockito.when(displayService.getAllByProfileId((profile.getProfileId()))).thenReturn(displayFromdb);

        //Act
        JsonResponse actualOutput = displayController.getAllByProfileId(profile.getProfileId());


        //assert
        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void getAllByProfileIdFail() {

        //Arrange
        List<Display> displayFromdb= new ArrayList<>();
        Display display = new Display(1, null, profile, null, "test");
        JsonResponse expectedOutput = new JsonResponse(false, "No displays match this profileId", null);
        Mockito.when(displayService.getAllByProfileId((2))).thenReturn(null);

        //Act
        JsonResponse actualOutput = displayController.getAllByProfileId(2);


        //assert
        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void getOneByDisplayId() {

        //Arrange
        Display display = new Display(3,null,profile,null,"test");
        JsonResponse expectedOutput = new JsonResponse(true, "Displaying display with displayId: " + display.getDisplayId(), display);
        Mockito.when(displayService.getOneByDisplayId((display.getDisplayId()))).thenReturn(display);
        //Act
        JsonResponse actualOutput = displayController.getOneByDisplayId(display.getDisplayId());


        //Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getOneByDisplayIdFail() {

        //Arrange
        Display display = new Display(3,null,profile,null,"test");
        JsonResponse expectedOutput = new JsonResponse(false, "There are no displays matching this displayId", null);
        Mockito.when(displayService.getOneByDisplayId((display.getDisplayId()))).thenReturn(null);
        //Act
        JsonResponse actualOutput = displayController.getOneByDisplayId(display.getDisplayId());


        //Assert
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    void deleteDisplay() {

        //Arrange
        Display display = new Display(3,null,profile,null,"test");
        Mockito.when(displayService.getOneByDisplayId(display.getDisplayId())).thenReturn(display);
        //Act
        displayController.deleteDisplay(3);

        //Assert
        Mockito.verify(displayService).deleteDisplay(display.getDisplayId());
    }

    @Test
    void createDisplay() {

        //Arrange
        Display display = new Display(3,null,profile,null,"test");
        JsonResponse expectedOutput = new JsonResponse(true, "Display has been created", display);
        Mockito.when(displayService.createDisplay(display)).thenReturn(display);
        //Act
        JsonResponse actualOutput = displayController.createDisplay(display);


        //Assert
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    void createDisplayFail() {

        //Arrange
        Display display = new Display(3,null,profile,null,"test");
        JsonResponse expectedOutput = new JsonResponse(false, "Display needs to include image or description", null);
        Mockito.when(displayService.createDisplay(display)).thenReturn(null);
        //Act
        JsonResponse actualOutput = displayController.createDisplay(display);


        //Assert
        assertEquals(expectedOutput, actualOutput);

    }


    @Test
    void addLikeToDisplay() {

        //Arrange
        List<Profile> Liker = new ArrayList<>();
        Liker.add(profile);
        Display display = new Display(3,null,profile, Liker,"test");
        JsonResponse expectedOutput = new JsonResponse(true, "Profile with profileId: " + profile.getProfileId() + "has liked Display with displayId: " + display.getDisplayId(), display);
        Mockito.when(displayService.addToLike(profile.getProfileId(), display.getDisplayId())).thenReturn(display);
        //Act
        JsonResponse actualOutput = displayController.changeLikeInDisplay(profile.getProfileId(), display.getDisplayId());


        //Assert
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    void addLikeToDisplayFail() {

        //Arrange
        List<Profile> Liker = new ArrayList<>();
        Liker.add(profile);
        Display display = new Display(3,null,profile, Liker,"test");
        JsonResponse expectedOutput = new JsonResponse(false, "Profile has already liked this post", null);
        Mockito.when(displayService.addToLike(profile.getProfileId(), display.getDisplayId())).thenReturn(null);
        //Act
        JsonResponse actualOutput = displayController.changeLikeInDisplay(profile.getProfileId(), display.getDisplayId());


        //Assert
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    void getAllLikersForOneDisplay() {

        //Arrange
        List<Profile> expectedOutPut = new ArrayList<>();
        Profile profile1 = new Profile(2,"dcft", "pass", "Neicey", "Fontenot", null, "dcft@email.com");
        expectedOutPut.add(profile1);
        Display display = new Display(1, null, profile, expectedOutPut, "test");
        Mockito.when(displayService.getAllLikers(display.getDisplayId())).thenReturn(expectedOutPut);

        //Act
        List<Profile> actualOutput = displayController.getAllLikersForOneDisplay(display.getDisplayId());

        //Assert
        assertEquals(expectedOutPut, actualOutput);
    }
}