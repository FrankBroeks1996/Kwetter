package dao;

import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext(name = "kwetterPU")
    private EntityManager em;

    public UserDAO(){}

    public void addUser(User user){
        em.persist(user);
    }

    public void editUser(User user){
        em.merge(user);
    }

    public void removeUser(User user){
        em.remove(user);
    }

    public List<User> getAllUsers(){
        Query query = em.createNamedQuery("User.getAllUsers", User.class);
        return query.getResultList();
    }

    public User getUserByName(String name){
        return em.createNamedQuery("User.getUserByName", User.class).setParameter("name", name).getSingleResult();
    }

    public void followUser(User currentUser, User userToBeFollowed){
        userToBeFollowed.getFollowers().add(currentUser);
        em.merge(userToBeFollowed);
    }

    public List<User> getAllFollowers(User user){
        return user.getFollowers();
    }

    public List<User> getAllFollowing(User user){
        return user.getFollowing();
    }

    public boolean login(User user){
        User u = em.createNamedQuery("User.login", User.class).setParameter("username", user.getUsername()).setParameter("password", user.getPassword()).getSingleResult();
        if(u == null){
            return false;
        }else{
            return true;
        }
    }
}
