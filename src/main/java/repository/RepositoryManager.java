package repository;

import model.SimpleProperty;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class RepositoryManager {

    @Inject
    private EntityManager em;

    public List<SimpleProperty>  queryCache(){
        Query query = em.createQuery("FROM SimpleProperty");

        List <SimpleProperty> list =  query.getResultList();
        return list;
    }
}
