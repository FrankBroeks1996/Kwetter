package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);

    void editUser(User user);

    User getUserByName(String name);

    void followUser(User currentUser, User userToBeFollowed);

    List<User> getAllFollowers(User user);

    List<User> getAllFollowing(User user);

    List<User> getAllUsers();

    boolean login(User user);
}
