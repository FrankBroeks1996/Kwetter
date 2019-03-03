package dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAOImplTest extends UserDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void init(){
        emf = Persistence.createEntityManagerFactory("KwetterTestPU");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

    @Before
    public void begin() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.setEm(em);
        setUserDAO(userDAO);
    }

    @Override
    public void refresh(Object object){
        em.refresh(object);
    }

    @After
    public void rollbackTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        if (em.isOpen()) {
            em.close();
        }
    }
}
