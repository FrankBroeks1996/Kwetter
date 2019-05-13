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

    List<Kweet> getAllUserKweets(User user);

    List<Kweet> getDashboard(User user, int resultPage, int resultSize);

    List<Kweet> getSearchResult(String searchQuery, int resultPage, int resultSize);
}
