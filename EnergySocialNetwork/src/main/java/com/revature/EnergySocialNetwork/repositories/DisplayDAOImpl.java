package com.revature.EnergySocialNetwork.repositories;

import com.revature.EnergySocialNetwork.models.Display;
import org.hibernate.Session;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DisplayDAOImpl implements DisplayDAO{

    @PersistenceContext
    EntityManager em;

    /**
     *
     * @return the list of displays made that are stored in the database
     */
    @Override
    public List<Display> getAll() {
        Session session = em.unwrap(Session.class);

        return session.createQuery("from Display", Display.class).getResultList();
    }

    /**
     *
     * @param profileId is the Id of the user
     * @return makes a list of the user's information given their Id (username,password,etc.)
     */
    @Override
    public List<Display> getAllByProfileId(Integer profileId) {
        Session session = em.unwrap(Session.class);

        return session.createQuery("from Display display where profile = '" + profileId + "'", Display.class).getResultList();
    }

    /**
     *
     * @param displayId is the Id of the display
     * @return grabs a single display based off it's Id
     */
    @Override
    public Display getOne(Integer displayId) {
        Session session = em.unwrap(Session.class);

        return session.get(Display.class, displayId);
    }

    /**
     *
     * @param display variable referring to the Display model
     * @return a new display created and then stored within the database
     */
    @Override
    public Integer createDisplay(Display display) {
        Session session = em.unwrap(Session.class);
        return (Integer) session.save(display);
    }

    /**
     *
     * Deletes a display within the database
     * @param display variable referring to a Display model
     */
    @Override
    public void deleteDisplay(Display display) {
        Session session = em.unwrap(Session.class);

        session.delete(display);

    }
}
