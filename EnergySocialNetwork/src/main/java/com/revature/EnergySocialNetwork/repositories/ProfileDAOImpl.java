package com.revature.EnergySocialNetwork.repositories;

import com.revature.EnergySocialNetwork.models.Profile;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProfileDAOImpl implements ProfileDAO{

    @PersistenceContext
    EntityManager em;

    /**
     *
     * @return the list of profiles created that are stored in the database
     */
    @Override
    public List<Profile> getAll() {
        Session session = em.unwrap(Session.class);

        return session.createQuery("from Profile", Profile.class).getResultList();
    }

    /**
     *
     * @param profileId is the Id of the user
     * @return a single user from the database when the Id is passed
     */
    @Override
    public Profile getOne(Integer profileId) {
        Session session = em.unwrap(Session.class);

        return session.get(Profile.class, profileId);
    }

    /**
     *
     * @param profile variable referring to the Profile model
     * @return a new profile created and then stored within the database
     */
    @Override
    public Integer createProfile(Profile profile) {
        Session session = em.unwrap(Session.class);

        return (Integer) session.save(profile);
    }

    /**
     *
     * User can modify/edit their profile information then save/update changes to the database
     * @param profile variable referring to the Profile model
     */
    @Override
    public void updateProfile(Profile profile) {
        Session session = em.unwrap(Session.class);

        session.update(profile);

    }

    /**
     *
     * Deletes a profile within the database
     * @param profile variable referring to the Profile model
     */
    @Override
    public void deleteProfile(Profile profile) {
        Session session = em.unwrap(Session.class);

        session.delete(profile);

    }

    /**
     *
     * @param username variable that the user inputs when profile is created
     * @return results of a user's information based on their username
     */
    @Override
    public Profile getOneByUsername(String username) {
        Session session = em.unwrap(Session.class);


        return session.createQuery("from Profile where username = '" + username + "'", Profile.class).getSingleResult();
    }
}
