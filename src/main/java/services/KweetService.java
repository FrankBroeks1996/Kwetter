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

    @EJB(beanName = "UserDAOImpl")
    private UserDAO userDAO;

    @EJB(beanName = "KweetDAOImpl")
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

    public List<Kweet> getAllUserKweets(User user){
        return kweetDAO.getAllUserKweets(user);
    }

    public List<Kweet> getDashboard(User user, int resultPage, int resultSize){
        return kweetDAO.getDashboard(user, resultPage, resultSize);
    }

    public List<Kweet> getSearchResult(String searchQuery, int resultPage, int resultSize){return kweetDAO.getSearchResult(searchQuery, resultPage, resultSize);}
}
