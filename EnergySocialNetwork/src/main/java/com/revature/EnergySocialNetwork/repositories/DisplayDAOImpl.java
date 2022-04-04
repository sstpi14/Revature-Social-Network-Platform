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

    @Override
    public List<Display> getAll() {
        Session session = em.unwrap(Session.class);

        return session.createQuery("from Display", Display.class).getResultList();
    }

    @Override
    public List<Display> getAllByProfileId(Integer profileId) {
        Session session = em.unwrap(Session.class);

        return session.createQuery("from Display display where profile = '" + profileId + "'", Display.class).getResultList();
    }

    @Override
    public Display getOne(Integer displayId) {
        Session session = em.unwrap(Session.class);

        return session.get(Display.class, displayId);
    }

    @Override
    public Integer createDisplay(Display display) {
        Session session = em.unwrap(Session.class);
        return (Integer) session.save(display);
    }

    @Override
    public void deleteDisplay(Display display) {
        Session session = em.unwrap(Session.class);

        session.delete(display);

    }
}
