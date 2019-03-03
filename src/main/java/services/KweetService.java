package services;

import dao.KweetDAO;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.Kweet;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    public List<Kweet> getAllKweets(User user){
        return user.getKweets();
    }

    public List<Kweet> getDashboard(User user){
        return kweetDAO.getDashboard(user);
    }
}
