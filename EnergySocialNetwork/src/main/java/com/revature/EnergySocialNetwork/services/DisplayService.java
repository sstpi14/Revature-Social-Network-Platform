package com.revature.EnergySocialNetwork.services;

import com.revature.EnergySocialNetwork.models.Display;
import com.revature.EnergySocialNetwork.models.Profile;
import com.revature.EnergySocialNetwork.repositories.DisplayDAO;
import com.revature.EnergySocialNetwork.repositories.ProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DisplayService {

    private DisplayDAO displayDAO;
    private ProfileDAO profileDAO;

    @Autowired
    public DisplayService (DisplayDAO displayDAO, ProfileDAO profileDAO) {
        this.displayDAO = displayDAO;
        this.profileDAO = profileDAO;
    }


    public Display createDisplay(Display display) {
        Integer displayId = displayDAO.createDisplay(display);

        Display displayFromDb = displayDAO.getOne(displayId);

        Profile profile = profileDAO.getOne(displayFromDb.getProfile().getProfileId());

        displayFromDb.setProfile(profile);

        return displayFromDb;
    }

    public List<Display> getAll() {
        return displayDAO.getAll();
    }

    public Display getOneByDisplayId(Integer displayId) {
        return displayDAO.getOne(displayId);
    }

    public List<Display> getAllByProfileId(Integer profileId) {

        return displayDAO.getAllByProfileId(profileId);
    }
    
    public void deleteDisplay(Integer displayId) {
        displayDAO.deleteDisplay(getOneByDisplayId(displayId));
    }

    public Display addToLike(Integer profileId,Integer displayId){
        Display display = displayDAO.getOne(displayId);

        Profile profile = profileDAO.getOne(profileId);

        List<Profile> profileThatHaveLiked = display.getLikers();

        for (int i = 0; i<profileThatHaveLiked.size();i++){
            if (profileThatHaveLiked.get(i).equals(profile)){
                return null;
            }
        }

        profileThatHaveLiked.add(profile);

        display.setLikers(profileThatHaveLiked);

        return display;
    }

    public List<Profile> getAllLikers(Integer displayId){
        Display display = displayDAO.getOne(displayId);

        List<Profile> Likers = display.getLikers();

        return Likers;
    }

}
