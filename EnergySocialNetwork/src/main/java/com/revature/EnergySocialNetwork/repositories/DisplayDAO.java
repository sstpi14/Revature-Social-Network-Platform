package com.revature.EnergySocialNetwork.repositories;

import com.revature.EnergySocialNetwork.models.Display;
import com.revature.EnergySocialNetwork.models.Profile;

import java.util.List;

public interface DisplayDAO {
    List<Display> getAll();
    List<Display> getAllByProfileId(Integer profileId);
    Display getOne(Integer displayId);
    Integer createDisplay(Display display);
    void deleteDisplay(Display display);
    void addProfileToLike(Profile profile);

}
