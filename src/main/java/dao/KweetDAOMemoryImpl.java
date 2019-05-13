package dao;

import memory.InMemoryDatabase;
import model.Kweet;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Stateless
@Alternative
public class KweetDAOMemoryImpl implements KweetDAO {

    @Override
    public void addKweet(Kweet kweet) {
        int id = 0;
        for (Kweet k : InMemoryDatabase.getKweets()){
            if(id <= k.getId()){
                id = k.getId();
            }
        }
        id++;
        kweet.setId(id);

        InMemoryDatabase.getKweets().add(kweet);

        if(kweet.getAuthor() != null){
            for (User u : InMemoryDatabase.getUsers()){
                if(u.getId() == kweet.getAuthor().getId()){
                    u.getKweets().add(kweet);
                }
            }
        }
    }

    @Override
    public void editKweet(Kweet kweet) {
        for(Kweet k : InMemoryDatabase.getKweets()){
            if(k.getId() == kweet.getId()){
                k.setMessage(kweet.getMessage());
            }
        }
    }

    @Override
    public void removeKweet(Kweet kweet) {
        InMemoryDatabase.getKweets().remove(kweet);

        for (User u : InMemoryDatabase.getUsers()){
            if(u.getUsername() == kweet.getAuthor().getUsername()){
                u.getKweets().remove(kweet);
            }
        }
    }

    @Override
    public Kweet getKweetById(int id) {
        for(Kweet k : InMemoryDatabase.getKweets()){
            if(k.getId() == id){
                return k;
            }
        }
        throw new NoResultException();
    }

    @Override
    public void heartKweet(User user, Kweet kweet) {
        for(Kweet k : InMemoryDatabase.getKweets()){
            if(k.getId() == kweet.getId()){
                k.getHearts().add(user);
            }
        }
    }

    @Override
    public List<Kweet> getAllUserKweets(User user) {
        return null;
    }

    @Override
    public List<Kweet> getDashboard(User user, int resultPage, int resultSize) {
        List<Kweet> kweets = new ArrayList<>();
        kweets.addAll(user.getKweets());

        for (User u : user.getFollowing()){
            kweets.addAll(u.getKweets());
        }

        return kweets;
    }

    @Override
    public List<Kweet> getSearchResult(String searchQuery, int resultPage, int resultSize) {
        return null;
    }
}
