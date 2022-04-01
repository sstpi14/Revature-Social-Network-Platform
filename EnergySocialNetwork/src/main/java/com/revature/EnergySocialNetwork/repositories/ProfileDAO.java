package com.revature.EnergySocialNetwork.repositories;

import com.revature.EnergySocialNetwork.models.Profile;

import java.util.List;

public interface ProfileDAO {
    List<Profile> getAll();
    Profile getOne(Integer profileId);
    Integer createProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfile(Profile profile);

    Profile getOneByUsername(String username);
}
