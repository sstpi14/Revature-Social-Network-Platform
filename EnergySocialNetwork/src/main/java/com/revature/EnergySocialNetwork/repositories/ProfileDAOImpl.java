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

    @Override
    public List<Profile> getAll() {
        Session session = em.unwrap(Session.class);

        return session.createQuery("from Profile", Profile.class).getResultList();
    }

    @Override
    public Profile getOne(Integer profileId) {
        Session session = em.unwrap(Session.class);

        return session.get(Profile.class, profileId);
    }

    @Override
    public Integer createProfile(Profile profile) {
        Session session = em.unwrap(Session.class);

        return (Integer) session.save(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        Session session = em.unwrap(Session.class);

        session.update(profile);

    }

    @Override
    public void deleteProfile(Profile profile) {
        Session session = em.unwrap(Session.class);

        session.delete(profile);

    }

    @Override
    public Profile getOneByUsername(String username) {
        Session session = em.unwrap(Session.class);

        return session.createQuery("from Profile where username = '" + username + "'", Profile.class).getSingleResult();
    }
}
