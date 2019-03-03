package dao;

import model.Kweet;
import model.User;

import java.util.List;

public interface KweetDAO {
    void addKweet(Kweet kweet);

    void editKweet(Kweet kweet);

    void removeKweet(Kweet kweet);

    Kweet getKweetById(int id);

    void heartKweet(User user, Kweet kweet);

    List<Kweet> getDashboard(User user);
}
