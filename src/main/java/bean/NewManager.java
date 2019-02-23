package bean;

import dao.UserDAO;
import model.User;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class NewManager {

//    @Inject
//    private UserDAO userDAO;
//
//    public void addUser(){
//        User user = new User();
//        user.setUsername("TestUser");
//        userDAO.addUser(user);
//
//        User user2 = userDAO.getUserByName("TestUser");
//
//        System.out.println(user2.getUsername());
//    }
}
