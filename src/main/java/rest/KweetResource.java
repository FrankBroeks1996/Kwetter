package rest;

import dto.KweetDTO;
import model.Kweet;
import model.User;
import org.jboss.logging.annotations.Pos;
import security.JWTTokenNeeded;
import services.KweetService;
import services.UserService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

@Path("kweet")
public class KweetResource {

    @EJB
    private KweetService kweetService;

    @EJB
    private UserService userService;

    @Context
    private SecurityContext context;

    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public void postKweet(KweetDTO kweetDTO){
        Principal principal = context.getUserPrincipal();
        String username = principal.getName();
        Kweet kweet = new Kweet(kweetDTO);

        kweetService.postKweet(username, kweet);
    }

    @POST
    @Path("heart")
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public void heartKweet(KweetDTO kweetDTO){
        Principal principal = context.getUserPrincipal();
        String username = principal.getName();
        Kweet kweet = new Kweet(kweetDTO);

        kweetService.heartKweet(username, kweet);
    }

    @POST
    @Path("removeKweet")
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public void removeKweet(KweetDTO kweetDTO){
        Kweet kweet = new Kweet(kweetDTO);
        kweetService.removeKweet(kweet);
    }

    @POST
    @Path("getAllKweets")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<KweetDTO> getAllKweets(){
        Principal principal = context.getUserPrincipal();
        User currentUser = userService.getUserByName(principal.getName());


    }
}