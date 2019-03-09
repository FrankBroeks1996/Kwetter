package dao;

import model.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
@Default
@Named("UserDAOImpl")
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(name = "kwetterPU")
    private EntityManager em;

    public UserDAOImpl(){}

    public void setEm(EntityManager em){
        this.em = em;
    }

    public void addUser(User user){
        em.persist(user);
    }

    public void editUser(User user){
        em.merge(user);
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
