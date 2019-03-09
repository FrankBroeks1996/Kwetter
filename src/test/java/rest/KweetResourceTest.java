package rest;

import dao.KweetDAO;
import dao.KweetDAOMemoryImpl;
import dao.UserDAO;
import dao.UserDAOMemoryImpl;
import dto.KweetDTO;
import memory.InMemoryDatabase;
import model.Kweet;
import model.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import services.KweetService;
import services.UserService;

import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@RunAsClient
public class KweetResourceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(KweetResource.class)
                .addClass(KweetDAO.class)
                .addClass(KweetDAOMemoryImpl.class)
                .addClass(Kweet.class)
                .addClass(User.class)
                .addClass(InMemoryDatabase.class)
                .addClass(KweetService.class)
                .addClass(UserService.class)
                .addClass(KweetDTO.class)
                .addClass(UserDAO.class)
                .addClass(UserDAOMemoryImpl.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    private URI _baseURL;

    private Client _client;
    private WebTarget _echoEndpointTarget;
    private WebTarget _userEndpointTarget;

    @Before
    public void init(){
        _client = ClientBuilder.newClient();
        _echoEndpointTarget = _client.target(_baseURL).path("api/v1");
        _userEndpointTarget = _client.target(_baseURL).path("api/kweet");
    }

    @Test
    public void postKweet() throws Exception {
        Response response = _echoEndpointTarget.request(MediaType.TEXT_PLAIN_TYPE).get();
        assertEquals(401, response.getStatus());
    }

    @Test
    public void heartKweet() throws Exception {
    }

    @Test
    public void removeKweet() throws Exception {
    }

    @Test
    public void getAllKweets() throws Exception {
    }

    @Test
    public void getDashboard() throws Exception {
    }

    @After
    public void cleanUp(){
        InMemoryDatabase.setUsers(new ArrayList<>());
        InMemoryDatabase.setUsers(new ArrayList<>());
    }
}
