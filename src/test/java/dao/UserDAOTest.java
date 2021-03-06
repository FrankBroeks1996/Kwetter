package dao;

import model.User;
import org.junit.Test;

import javax.ejb.EJB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {

    @EJB
    private UserDAO _userDAO;

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("Username1");
        user.setBio("Bio");
        _userDAO.addUser(user);

        assertEquals("Username1", _userDAO.getUserByName("Username1").getUsername());
        assertEquals("Bio", _userDAO.getUserByName("Username1").getBio());
    }

    @Test
    public void editUser() throws Exception {
        User user = new User();
        user.setUsername("Username2");
        user.setBio("Bio");
        _userDAO.addUser(user);

        user.setUsername("NewUsername");
        _userDAO.editUser(user);

        assertEquals("NewUsername", _userDAO.getUserByName("NewUsername").getUsername());
        assertEquals("Bio", _userDAO.getUserByName("NewUsername").getBio());

    }

    @Test
    public void getUserByName() throws Exception {
        User user = new User();
        user.setUsername("Username3");
        _userDAO.addUser(user);

        User user2 = _userDAO.getUserByName("Username3");

        assertEquals("Username3", user2.getUsername());
    }

    @Test
    public void followUser() throws Exception {
        User user1 = new User();
        user1.setUsername("User1");

        User user2 = new User();
        user2.setUsername("User2");

        _userDAO.addUser(user1);
        _userDAO.addUser(user2);

        _userDAO.followUser(user1, user2);

        assertEquals(1, user2.getFollowers().size());
        assertEquals(0, user1.getFollowers().size());
    }

    @Test
    public void getAllFollowers() throws Exception {
        User user1 = new User();
        user1.setUsername("User3");

        User user2 = new User();
        user2.setUsername("User4");

        _userDAO.addUser(user1);
        _userDAO.addUser(user2);

        _userDAO.followUser(user1, user2);

        assertEquals(1, user2.getFollowers().size());
    }

    @Test
    public void getAllFollowing() throws Exception {
        User user1 = new User();
        user1.setUsername("User5");

        User user2 = new User();
        user2.setUsername("User6");

        _userDAO.addUser(user1);
        _userDAO.addUser(user2);

        _userDAO.followUser(user1, user2);

        user1 = _userDAO.getUserByName("User5");

        assertEquals(1, _userDAO.getAllFollowing(user1).size());
    }

    @Test
    public void login() throws Exception {
        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword("pass");
        _userDAO.addUser(user1);

        assertTrue(_userDAO.login(user1));
    }
}
