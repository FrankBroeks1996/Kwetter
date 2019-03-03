package services;

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
import javax.jws.soap.SOAPBinding;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserServiceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserService.class)
                .addClass(Kweet.class)
                .addClass(UserDAO.class)
                .addClass(UserDAOMemoryImpl.class)
                .addClass(User.class)
                .addClass(InMemoryDatabase.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private UserService _userService;

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("User");
        _userService.addUser(user);

        assertEquals(1, InMemoryDatabase.getUsers().size());
    }

    @Test
    public void getUserByName() throws Exception {
        User user = new User();
        user.setUsername("User");
        _userService.addUser(user);

        assertEquals("User", _userService.getUserByName("User").getUsername());
    }

    @Test
    public void editUser() throws Exception {
        User user = new User();
        user.setUsername("User");
        user.setBio("Bio");
        _userService.addUser(user);

        user = _userService.getUserByName("User");
        user.setBio("New bio");
        _userService.editUser(user);

        assertEquals("New bio", _userService.getUserByName("User").getBio());
    }

    @Test
    public void login() throws Exception {
        User user = new User();
        user.setUsername("User");
        user.setPassword("Pass");
        _userService.addUser(user);

        assertTrue(_userService.login(user));
    }

    @Test
    public void getAllFollowing() throws Exception {
        User user1 = new User();
        user1.setUsername("User1");
        _userService.addUser(user1);

        User user2 = new User();
        user2.setUsername("User2");
        _userService.addUser(user2);

        assertEquals(0, _userService.getUserByName("User1").getFollowing().size());

        _userService.followUser(user1, user2);

        assertEquals(1, _userService.getUserByName("User1").getFollowing().size());
    }

    @Test
    public void getAllFollowers() throws Exception {
        User user1 = new User();
        user1.setUsername("User1");
        _userService.addUser(user1);

        User user2 = new User();
        user2.setUsername("User2");
        _userService.addUser(user2);

        assertEquals(0, _userService.getUserByName("User2").getFollowers().size());

        _userService.followUser(user1, user2);

        assertEquals(1, _userService.getUserByName("User2").getFollowers().size());
    }

    @Test
    public void followUser() throws Exception {
        User user1 = new User();
        user1.setUsername("User1");
        _userService.addUser(user1);

        User user2 = new User();
        user2.setUsername("User2");
        _userService.addUser(user2);

        assertEquals(0, _userService.getUserByName("User1").getFollowing().size());
        assertEquals(0, _userService.getUserByName("User2").getFollowers().size());

        _userService.followUser(user1, user2);

        assertEquals(1, _userService.getUserByName("User1").getFollowing().size());
        assertEquals(1, _userService.getUserByName("User2").getFollowers().size());
    }

    @After
    public void cleanInMemoryDatabase(){
        InMemoryDatabase.setKweets(new ArrayList<>());
        InMemoryDatabase.setUsers(new ArrayList<>());
    }

}
