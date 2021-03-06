package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@Stateless
public class UserService {

    @EJB(beanName = "UserDAOImpl")
    private UserDAO userDAO;

    public void addUser(User user){
        userDAO.addUser(user);
    }

    public User getUserByName(String name){
        return userDAO.getUserByName(name);
    }

    public void editUser(User user){
        userDAO.editUser(user);
    }

    public boolean login(User user){return userDAO.login(user);}

    public Set<User> getAllFollowing(User user){
        return userDAO.getAllFollowing(user);
    }

    public Set<User> getAllFollowers(User user){
        return userDAO.getAllFollowers(user);
    }

    public void followUser(User currentUser, User userToBeFollowed){
        userDAO.followUser(currentUser, userToBeFollowed);
    }

    public void unFollowUser(User currentUser, User userToBeUnFollowed){
        userDAO.unFollowUser(currentUser, userToBeUnFollowed);
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public boolean isFollowing(User currentUser, User checkUser){
        return userDAO.isFollowing(currentUser, checkUser);
    }
}
