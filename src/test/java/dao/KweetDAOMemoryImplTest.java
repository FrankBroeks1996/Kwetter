package dao;

import memory.InMemoryDatabase;
import model.Kweet;
import model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(Arquillian.class)
public class KweetDAOMemoryImplTest extends KweetDAOTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(KweetDAO.class)
                .addClass(KweetDAOMemoryImpl.class)
                .addClass(KweetDAOTest.class)
                .addClass(Kweet.class)
                .addClass(User.class)
                .addClass(InMemoryDatabase.class)
                .addClass(UserDAO.class)
                .addClass(UserDAOMemoryImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @After
    public void clearInMemoryDatabase(){
        InMemoryDatabase.setUsers(new ArrayList<>());
        InMemoryDatabase.setKweets(new ArrayList<>());
    }
}
