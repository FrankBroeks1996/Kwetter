package dao;

import model.Kweet;
import model.User;
import org.junit.Assert;
import org.junit.Test;

import javax.ejb.EJB;
import javax.persistence.NoResultException;

import static org.junit.Assert.*;

public class KweetDAOTest {

    @EJB
    private KweetDAO _kweetDAO;

    @EJB
    private UserDAO _userDAO;

    @Test
    public void addKweet(){
        Kweet kweet = new Kweet();
        kweet.setMessage("Message");
        _kweetDAO.addKweet(kweet);

        assertEquals("Message", _kweetDAO.getKweetById(kweet.getId()).getMessage());
    }

    @Test
    public void editKweet() throws Exception {
        Kweet kweet = new Kweet();
        kweet.setMessage("This is a nice kweet");
        _kweetDAO.addKweet(kweet);

        kweet.setMessage("This is not a nice kweet");
        _kweetDAO.editKweet(kweet);

        Kweet kweet2 = _kweetDAO.getKweetById(kweet.getId());
        Assert.assertEquals("This is not a nice kweet", kweet2.getMessage());
    }

    @Test
    public void removeKweet() throws Exception {
        Kweet kweet = new Kweet();
        kweet.setMessage("Message");
        _kweetDAO.addKweet(kweet);
        kweet = _kweetDAO.getKweetById(kweet.getId());

        _kweetDAO.removeKweet(kweet);
    }

    @Test
    public void getKweetById() throws Exception {
        Kweet kweet = new Kweet();
        kweet.setMessage("Message");
        _kweetDAO.addKweet(kweet);

        Assert.assertEquals(_kweetDAO.getKweetById(kweet.getId()).getId(), kweet.getId());
        Assert.assertEquals(_kweetDAO.getKweetById(kweet.getId()).getMessage(), kweet.getMessage());
    }

    @Test
    public void heartKweet() throws Exception {
        Kweet kweet = new Kweet();
        kweet.setMessage("Message");
        _kweetDAO.addKweet(kweet);

        User user = new User();
        user.setUsername("username");
        _userDAO.addUser(user);

        _kweetDAO.heartKweet(user, kweet);

        Assert.assertEquals(1, kweet.getHearts().size());
    }

    @Test
    public void getDashboard() throws Exception{
        User user1 = new User();
        user1.setUsername("User1");
        User user2 = new User();
        user2.setUsername("User2");
        User user3 = new User();
        user3.setUsername("User3");

        _userDAO.addUser(user1);
        _userDAO.addUser(user2);
        _userDAO.addUser(user3);

        _userDAO.followUser(user1, user2);

        Kweet kweet1 = new Kweet();
        kweet1.setAuthor(user1);
        _kweetDAO.addKweet(kweet1);

        Kweet kweet2 = new Kweet();
        kweet2.setAuthor(user2);
        _kweetDAO.addKweet(kweet2);

        Kweet kweet3 = new Kweet();
        kweet3.setAuthor(user3);
        _kweetDAO.addKweet(kweet3);

        user1 = _userDAO.getUserByName("User1");

        Assert.assertEquals(2, _kweetDAO.getDashboard(user1, 1, 10).size());
    }
}
