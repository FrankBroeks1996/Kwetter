package dao;

import model.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

    private static UserDAO _userDAO;

    protected static void setUserDAO(UserDAO u) {_userDAO = u;}

    protected void refresh(Object object){}
    protected void flush(){}

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("Username");
        _userDAO.addUser(user);

        assertEquals("Username", _userDAO.getUserByName("Username").getUsername());
    }

    @Test
    public void editUser() throws Exception {
        User user = new User();
        user.setUsername("Username");
        _userDAO.addUser(user);

        user.setUsername("NewUsername");
        _userDAO.editUser(user);

        assertEquals("NewUsername", _userDAO.getUserByName("NewUsername").getUsername());

    }

    @Test
    public void getUserByName() throws Exception {
        User user = new User();
        user.setUsername("Username");
        _userDAO.addUser(user);

        User user2 = _userDAO.getUserByName("Username");

        assertEquals("Username", user2.getUsername());
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

        user1 = _userDAO.getUserByName("User1");
        user2 = _userDAO.getUserByName("User2");

        assertEquals(1, user2.getFollowers().size());
        assertEquals(0, user1.getFollowers().size());
    }

    @Test
    public void getAllFollowers() throws Exception {
        User user1 = new User();
        user1.setUsername("User1");

        User user2 = new User();
        user2.setUsername("User2");

        _userDAO.addUser(user1);
        _userDAO.addUser(user2);

        _userDAO.followUser(user1, user2);

        user1 = _userDAO.getUserByName("User1");
        user2 = _userDAO.getUserByName("User2");

        assertEquals(1, user2.getFollowers().size());
    }

    @Test
    public void getAllFollowing() throws Exception {
        User user1 = new User();
        user1.setUsername("User1");

        User user2 = new User();
        user2.setUsername("User2");

        _userDAO.addUser(user1);
        _userDAO.addUser(user2);

        _userDAO.followUser(user1, user2);

        user1 = _userDAO.getUserByName("User1");
        user2 = _userDAO.getUserByName("User2");

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