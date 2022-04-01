package com.revature.RevatureSocialNetwork.repositories;

import com.revature.RevatureSocialNetwork.models.Profile;

public interface ProfileDAO {
    Profile getProfileGivenUsername(String username);
    void createProfile(Profile profile);
}
