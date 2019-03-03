package dao;

import memory.InMemoryDatabase;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;

public class UserDAOMemoryImplTest extends UserDAOTest{
    @Before
    public void begin() {
        setUserDAO(new UserDAOMemoryImpl());
    }

    @After
    public void cleanUp(){
        InMemoryDatabase.setKweets(new ArrayList<>());
        InMemoryDatabase.setUsers(new ArrayList<>());
    }
}
