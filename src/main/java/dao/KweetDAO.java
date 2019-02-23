package dao;

import model.Kweet;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class KweetDAO {

    @PersistenceContext(name = "kwetterPU")
    private EntityManager em;

    public void addKweet(Kweet kweet){
        em.persist(kweet);
    }

    public void editKweet(Kweet kweet){
        em.merge(kweet);
    }

    public void removeKweet(Kweet kweet){
        em.remove(kweet);
    }

    public Kweet getKweetById(int id){
        return em.createNamedQuery("Kweet.getKweetById", Kweet.class).setParameter("id", id).getSingleResult();
    }

    public void heartKweet(User user, Kweet kweet){
        kweet.getHearts().add(user);
    }

    public List<Kweet> getAllUserKweets(){
        Query query = em.createNamedQuery("Kweet.getAllUserKweets", Kweet.class);
        return query.getResultList();
    }
}
