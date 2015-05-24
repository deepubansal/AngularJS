package com.deepak.maps.seeme.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
public abstract class AbstractDao {
 
    @PersistenceContext
    private EntityManager entityManager;
 
    protected EntityManager getEntityManager() {
        return entityManager;
    }
 
    public void persist(Object entity) {
        getEntityManager().persist(entity);
    }
 
    public void delete(Object entity) {
        getEntityManager().remove(entity);
    }
}