package dao;

import model.Kweet;
import model.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Default
@Named("KweetDAOImpl")
public class KweetDAOImpl implements KweetDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public void addKweet(Kweet kweet){
        em.persist(kweet);
    }

    public void editKweet(Kweet kweet){
        em.merge(kweet);
    }

    public void removeKweet(Kweet kweet){
        kweet = getKweetById(kweet.getId());
        em.remove(kweet);
    }

    public Kweet getKweetById(int id){
        return em.createNamedQuery("Kweet.getKweetById", Kweet.class).setParameter("id", id).getSingleResult();
    }

    public void heartKweet(User user, Kweet kweet){
        kweet.getHearts().add(user);
    }

    public List<Kweet> getAllUserKweets(User user){
        return em.createNamedQuery("Kweet.getAllUserKweets", Kweet.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<Kweet> getDashboard(User user, int resultPage, int resultSize){
        return em.createNamedQuery("Kweet.getDashboard", Kweet.class)
                .setFirstResult((resultPage-1) * resultSize)
                .setMaxResults(resultSize)
                .setParameter("currentUser", user)
                .getResultList();
    }

    public List<Kweet> getSearchResult(String searchQuery, int resultPage, int resultSize){
        return em.createNamedQuery("Kweet.searchKweets", Kweet.class)
                .setFirstResult((resultPage-1) * resultSize)
                .setMaxResults(resultSize)
                .setParameter("searchQuery", "%" + searchQuery + "%").getResultList();
    }
}
