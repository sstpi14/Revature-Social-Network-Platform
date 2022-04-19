package com.revature.EnergySocialNetwork.prototype;

import com.revature.EnergySocialNetwork.models.Profile;

public class ProfilePrototype {
    public static Profile aProfile(){
        Profile profile = new Profile();
        profile.setProfileId(1);
        profile.setEmail("test@email.com");
        profile.setUsername("testee");
        profile.setFirst_name("tester");
        profile.setLast_name("testing");
        profile.setPassword("test123");

        return profile;


    }
}
