package com.revature.EnergySocialNetwork.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DisplayDAOImpl implements DisplayDAO{

    @PersistenceContext
    EntityManager em;
}
