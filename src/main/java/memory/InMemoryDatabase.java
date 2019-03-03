package memory;

import model.Kweet;
import model.User;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class InMemoryDatabase {
    private static List<User> users = new ArrayList<>();
    private static List<Kweet> kweets = new ArrayList<>();

    public static List<Kweet> getKweets() {
        return kweets;
    }

    public static void setKweets(List<Kweet> k) {
        kweets = k;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> u) {
        users = u;
    }
}
