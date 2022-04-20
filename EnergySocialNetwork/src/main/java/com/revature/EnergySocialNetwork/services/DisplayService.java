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

    /**
     *
     * @param displayDAO variable given to reference the DisplayDAO from repositories package
     * @param profileDAO variable given to reference the ProfileDAO from repositories package
     */
    @Autowired
    public DisplayService (DisplayDAO displayDAO, ProfileDAO profileDAO) {
        this.displayDAO = displayDAO;
        this.profileDAO = profileDAO;
    }

    /**
     *
     * @param display variable for when a display is sent to the backend
     * @return will retrieve the display stored within the database
     */
    public Display createDisplay(Display display) {

        if (display.getDescription() == null && display.getImg() == null) {
            return null;
        } else {
            Integer displayId = displayDAO.createDisplay(display);

            Display displayFromDb = displayDAO.getOne(displayId);

            Profile profile = profileDAO.getOne(displayFromDb.getProfile().getProfileId());

            displayFromDb.setProfile(profile);

            return displayFromDb;
        }
    }

    /**
     *
     * @return retrieves all display stored within the database
     */
    public List<Display> getAll() {
        return displayDAO.getAll();
    }

    /**
     * Grabs a single display from the arraylist of displayIds
     * @param displayId is the Id value for the displays
     * @return will only gather a single display within the array
     */
    public Display getOneByDisplayId(Integer displayId) {
        return displayDAO.getOne(displayId);
    }

    /**
     *
     * @param profileId is the Id of the user
     * @return all the profiles made in the database
     */
    public List<Display> getAllByProfileId(Integer profileId) {
        Profile profileFromDb = profileDAO.getOne(profileId);
        if (profileFromDb == null) {
            return null;
        }
        return displayDAO.getAllByProfileId(profileId);
    }

    /**
     * Deletes a display given the Id display
     * @param displayId is the Id value for a specific display made within the array
     */
    public void deleteDisplay(Integer displayId) {
        displayDAO.deleteDisplay(getOneByDisplayId(displayId));
    }

    public Display addToLike(Integer profileId,Integer displayId){
        Display display = displayDAO.getOne(displayId);

        Profile profile = profileDAO.getOne(profileId);

        List<Profile> profileThatHaveLiked = display.getLikers();

        for (int i = 0; i<profileThatHaveLiked.size();i++){
            if (profileThatHaveLiked.get(i).equals(profile)){
                profileThatHaveLiked.remove(profile);

                display.setLikers(profileThatHaveLiked);
                return display;
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
