package servers;

import com.google.gson.Gson;
import dto.KweetDTO;
import model.Kweet;
import model.User;
import services.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/kweets/{username}")
@Stateless
public class KweetServer {

    @EJB
    private UserService userService;

    private Gson gson = new Gson();

    private Session session;
    private static Set<KweetServer> endpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException {
        this.session = session;
        endpoints.add(this);
        users.put(session.getId(), username);
    }

    public void spreadKweet(KweetDTO kweet){
        User user = userService.getUserByName(kweet.getUsername());
        Set<User> followers = userService.getAllFollowers(user);

        for (KweetServer endpoint : endpoints){
            synchronized (kweet) {
                try {
                    if(followers.stream().filter(o -> o.getUsername().equals(users.get(endpoint.session.getId()))).findFirst().isPresent()) {
                        endpoint.session.getBasicRemote().sendText(gson.toJson(kweet, KweetDTO.class));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
