package com.revature.EnergySocialNetwork.services;

import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.repositories.ProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileDAO profileDAO;

    public Profile createOne(Profile profile) {
        Integer profileId = profileDAO.createProfile(profile);
        Profile profileFromDb = profileDAO.getOne(profileId);

        return profileFromDb;
    }

    public Profile getOne(Integer profileId) {
        return profileDAO.getOne(profileId);
    }

    public Profile updateOne(Profile profile) {
        profileDAO.updateProfile(profile);
        return profileDAO.getOne(profile.getProfileId());
    }

    public List<Profile> getAll() {
        return profileDAO.getAll();
    }

    public Profile getOneGivenUsername(String username) {
        return profileDAO.getOneByUsername(username);
    }

}
