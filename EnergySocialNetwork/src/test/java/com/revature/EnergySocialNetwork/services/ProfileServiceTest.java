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

import java.util.List;
import java.util.stream.Stream;

import static com.revature.EnergySocialNetwork.prototype.ProfilePrototype.aProfile;
import static org.junit.jupiter.api.Assertions.*;


class ProfileServiceTest {

    @MockBean
    private ProfileDAO profileDAO = Mockito.spy(ProfileDAO.class);


    private ProfileService profileService;

    public ProfileServiceTest(){
        this.profileService = new ProfileService(profileDAO);
    }

    Profile profile = new Profile(1,"test","test123","tester","testing",null,"test@email.com");

    @Test
    void createOne() {
        //arrange
        int profileId = 1;
        Integer expectedOutput = profileId;

        //act
        Profile actualOutput = profileService.createOne(profile);
        //assert
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void getOne() {
        aProfile();
        //arrange
        int profileId = 1;
        Profile expectedOutput = profile;


        //act
        Profile actualOutput = profileService.getOne(aProfile().getProfileId());
        //assert
        Assertions.assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void updateOne() {
        //arrange
        int profileId = 1;
        //act
        //assert
    }

    @Test
    void getAll() {
        //arrange
        int profileId = 1;
        //act
        //assert
    }

    @Test
    void getOneGivenUsername() {
        //arrange
        int profileId = 1;
        //act
        //assert
    }
}