package dao;

import memory.InMemoryDatabase;
import model.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@Stateless
@Alternative
public class UserDAOMemoryImpl implements UserDAO {

    @Override
    public void addUser(User user) {
        int id = 0;
        for (User u : InMemoryDatabase.getUsers()){
            if(id <= u.getId()){
                id = u.getId();
            }
        }
        id++;
        user.setId(id);

        InMemoryDatabase.getUsers().add(user);
    }

    @Override
    public void editUser(User user) {
        for(User u : InMemoryDatabase.getUsers()){
            if(u.getId() == user.getId()){
                u.setUsername(user.getUsername());
                u.setBio(user.getBio());
                u.setLocation(user.getLocation());
                u.setPassword(user.getPassword());
                u.setWebsite(user.getWebsite());
            }
        }
    }

    @Override
    public User getUserByName(String name) {
        for(User u : InMemoryDatabase.getUsers()){
            if(u.getUsername() == name){
                return u;
            }
        }
        return null;
    }

    @Override
    public void followUser(User currentUser, User userToBeFollowed) {
        User currentU = null;
        User uToBeFollowed = null;

        for(User u : InMemoryDatabase.getUsers()){
            if(u.getId() == currentUser.getId()){
                currentU = u;
            }else if(u.getId() == userToBeFollowed.getId()){
                uToBeFollowed = u;
            }
        }

        currentU.getFollowing().add(uToBeFollowed);
        uToBeFollowed.getFollowers().add(currentU);
    }

    @Override
    public void unFollowUser(User currentUser, User userToBeUnFollowed) {
        throw new NotImplementedException();
    }

    @Override
    public Set<User> getAllFollowers(User user) {
        for(User u : InMemoryDatabase.getUsers()){
            if(u.getId() == user.getId()){
                return u.getFollowers();
            }
        }
        return null;
    }

    @Override
    public Set<User> getAllFollowing(User user) {
        for(User u : InMemoryDatabase.getUsers()){
            if(u.getId() == user.getId()){
                return u.getFollowing();
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return InMemoryDatabase.getUsers();
    }

    @Override
    public boolean isFollowing(User currentUser, User checkUser) {
        return false;
    }

    @Override
    public boolean login(User user) {
        for(User u : InMemoryDatabase.getUsers()){
            if(u.getUsername() == user.getUsername() && u.getPassword() == user.getPassword()){
                return true;
            }
        }
        return false;
    }
}
