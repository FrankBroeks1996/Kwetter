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
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

@RunWith(Arquillian.class)
public class KweetDAOImplTest extends KweetDAOTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(KweetDAO.class)
                .addClass(KweetDAOImpl.class)
                .addClass(Kweet.class)
                .addClass(User.class)
                .addClass(Tag.class)
                .addClass(Role.class)
                .addClass(KweetDTO.class)
                .addClass(UserDTO.class)
                .addClass(KweetDAOTest.class)
                .addClass(UserDAO.class)
                .addClass(UserDAOImpl.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
