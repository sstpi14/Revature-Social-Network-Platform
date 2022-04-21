package com.revature.EnergySocialNetwork.services;

import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.repositories.ProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProfileService {

    private ProfileDAO profileDAO;

    /**
     *
     * @param profileDAO variable given to reference the ProfileDAO from repositories package
     */
    @Autowired
    public ProfileService(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    /**
     *
     * @param profile variable for a users profile information
     * @return a created profile sent into the database once information is passed
     */
    public Profile createOne(Profile profile) {
        String profileUN = profile.getUsername();
        String profileEmail = profile.getEmail();
        List<Profile> profileCheck = profileDAO.getAll();

        for(int i = 0; i<profileCheck.size();i++){
            if(Objects.equals(profileCheck.get(i).getEmail(), profileEmail) || Objects.equals(profileCheck.get(i).getUsername(), profileUN)){
                return null;
            }
        }
        Integer profileId = profileDAO.createProfile(profile);
        Profile profileFromDb = profileDAO.getOne(profileId);
        return profileFromDb;
    }

    /**
     *
     * @param profileId Ids of every user made within the database
     * @return a single profile throughout an arrayList given the Id
     */
    public Profile getOne(Integer profileId) {
        List<Profile> profileCheck = profileDAO.getAll();
        for(int i = 0; i<profileCheck.size();i++){
            if (Objects.equals(profileCheck.get(i).getProfileId(), profileId)){
                return profileDAO.getOne(profileId);
            }
        }
        return null;

    }

    /**
     *
     * @param profile variable for a users profile information
     * @return a modified/edited version of a user's profile and saves the new changes to their profile
     */
    public Profile updateOne(Profile profile) {
        /*Integer profileId = profile.getProfileId();
        Profile profileFromDb = getOne(profileId);
        if (profileFromDb == null) {
            return null;
        }*/

        profileDAO.updateProfile(profile);
        return profileDAO.getOne(profile.getProfileId());

    }

    /**
     *
     * @return the whole arrayList of profiles in the database
     */
    public List<Profile> getAll() {
        return profileDAO.getAll();
    }

    /**
     *
     * @param username a unique name the user passes to the database when registering
     * @return a user's information when username is passed
     */
    public Profile getOneGivenUsername(String username) {
        List<Profile> profileCheck = profileDAO.getAll();

        for(int i = 0; i<profileCheck.size();i++) {
            if (Objects.equals(profileCheck.get(i).getUsername(), username)){
                return profileDAO.getOneByUsername(username);
            }
        }
        return null;
    }

}
