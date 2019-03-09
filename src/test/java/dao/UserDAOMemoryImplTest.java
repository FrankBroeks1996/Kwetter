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
public class UserDAOMemoryImplTest extends UserDAOTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserDAO.class)
                .addClass(UserDAOMemoryImpl.class)
                .addClass(User.class)
                .addClass(UserDAOTest.class)
                .addClass(InMemoryDatabase.class)
                .addClass(Kweet.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @After
    public void cleanUp(){
        InMemoryDatabase.setKweets(new ArrayList<>());
        InMemoryDatabase.setUsers(new ArrayList<>());
    }
}
