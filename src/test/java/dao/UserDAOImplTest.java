package dao;

import dto.KweetDTO;
import dto.UserDTO;
import model.Kweet;
import model.Role;
import model.Tag;
import model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(Arquillian.class)
public class UserDAOImplTest extends UserDAOTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserDAO.class)
                .addClass(UserDAOImpl.class)
                .addClass(UserDAOTest.class)
                .addClass(User.class)
                .addClass(Role.class)
                .addClass(Kweet.class)
                .addClass(Tag.class)
                .addClass(KweetDTO.class)
                .addClass(UserDTO.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
