package services;

import dao.KweetDAO;
import dao.KweetDAOMemoryImpl;
import dao.UserDAO;
import dao.UserDAOMemoryImpl;
import memory.InMemoryDatabase;
import model.Kweet;
import model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(Arquillian.class)
public class KweetServiceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(KweetService.class)
                .addClass(UserDAO.class)
                .addClass(KweetDAO.class)
                .addClass(Kweet.class)
                .addClass(User.class)
                .addClass(UserDAOMemoryImpl.class)
                .addClass(KweetDAOMemoryImpl.class)
                .addClass(InMemoryDatabase.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private UserDAO _userDAO;

    @EJB
    private KweetService _kweetService;

    @Test
    public void postKweet() throws Exception {
        User user = new User();
        user.setUsername("User");
        _userDAO.addUser(user);

        Kweet kweet = new Kweet();
        kweet.setMessage("Kweet");
        _kweetService.postKweet("User", kweet);

        assertEquals(1, user.getKweets().size());
    }

    @Test
    public void heartKweet() throws Exception {
        User user1 = new User();
        user1.setUsername("User1");
        _userDAO.addUser(user1);

        User user2 = new User();
        user1.setUsername("User2");
        _userDAO.addUser(user2);

        Kweet kweet = new Kweet();
        kweet.setMessage("Kweet");
        _kweetService.postKweet("User1", kweet);

        _kweetService.heartKweet("User1", kweet);
        assertEquals(1, kweet.getHearts().size());

        _kweetService.heartKweet("User2", kweet);
        assertEquals(2, kweet.getHearts().size());
    }

    @Test
    public void removeKweet() throws Exception {
        User user = new User();
        user.setUsername("User");
        _userDAO.addUser(user);

        Kweet kweet = new Kweet();
        kweet.setMessage("Kweet");
        _kweetService.postKweet("User", kweet);

        assertEquals(1, user.getKweets().size());

        _kweetService.removeKweet(kweet);
        assertEquals(0, user.getKweets().size());
    }

    @Test
    public void getAllKweets() throws Exception {
        User user = new User();
        user.setUsername("User");
        _userDAO.addUser(user);

        Kweet kweet1 = new Kweet();
        kweet1.setMessage("Kweet1");
        _kweetService.postKweet("User", kweet1);

        assertEquals(1, user.getKweets().size());

        Kweet kweet2 = new Kweet();
        kweet2.setMessage("Kweet2");
        _kweetService.postKweet("User", kweet2);

        assertEquals(2, user.getKweets().size());
    }

    @Test
    public void getDashboard() throws Exception {
        User user1 = new User();
        user1.setUsername("User1");
        _userDAO.addUser(user1);

        User user2 = new User();
        user2.setUsername("User2");
        _userDAO.addUser(user2);

        Kweet kweet1 = new Kweet();
        kweet1.setMessage("Kweet1");
        _kweetService.postKweet("User1", kweet1);

        Kweet kweet2 = new Kweet();
        kweet2.setMessage("Kweet2");
        _kweetService.postKweet("User2", kweet2);

        assertEquals(1, _kweetService.getDashboard(user1).size());

        _userDAO.followUser(user1, user2);

        assertEquals(2, _kweetService.getDashboard(user1).size());
        assertEquals(1, _kweetService.getDashboard(user2).size());
    }

    @After
    public void clearInMemoryDatabase(){
        InMemoryDatabase.setUsers(new ArrayList<>());
        InMemoryDatabase.setKweets(new ArrayList<>());
    }
}
