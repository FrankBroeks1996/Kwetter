package services;

import dao.KweetDAO;
import dao.UserDAO;
import model.Kweet;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Set;

@Stateless
public class KweetService {

    @EJB
    private UserDAO userDAO;

    @EJB
    private KweetDAO kweetDAO;

    public void postKweet(String username, Kweet kweet){
        User user = userDAO.getUserByName(username);
        kweet.setAuthor(user);
        kweetDAO.addKweet(kweet);
    }

    public void heartKweet(String username, Kweet kweet){
        User user = userDAO.getUserByName(username);
        kweet = kweetDAO.getKweetById(kweet.getId());
        kweetDAO.heartKweet(user, kweet);
    }

    public void removeKweet(Kweet kweet){
        kweet = kweetDAO.getKweetById(kweet.getId());
        kweetDAO.removeKweet(kweet);
    }

    public void editUser(User user){userDAO.editUser(user);}

    public Set<Kweet> getAllKweets(User user){
        return user.getKweets();
    }
}
