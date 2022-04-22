package com.revature.EnergySocialNetwork.services;

import com.revature.EnergySocialNetwork.models.Display;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayServiceTest {


    Display display = new Display(1,null, null, null, "test");

    @Test
    void createDisplay() {
        //arrange: What I want, a display that has a profile
        Display expectedOutput = new Display(1, null, null, null, "test");



        //act: What I need, a list of displays

        //In the db is display without profile

        //assert:
    }

    @Test
    void getAll() {
    }

    @Test
    void getOneByDisplayId() {

    }

    @Test
    void getAllByProfileId() {
    }

    @Test
    void deleteDisplay() {
    }

    @Test
    void addToLike() {
    }

    @Test
    void getAllLikers() {
        //arrange
        //act
        //assert
    }
}