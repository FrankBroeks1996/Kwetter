package rest;

import dto.KweetDTO;
import model.Kweet;
import model.User;
import security.JWTTokenNeeded;
import servers.KweetServer;
import services.KweetService;
import services.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Path("kweet")
@Stateless
public class KweetResource {

    @EJB
    private KweetService kweetService;

    @EJB
    private UserService userService;

    @EJB
    private KweetServer kweetServer;

    @Context
    private SecurityContext context;

    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response postKweet(KweetDTO kweetDTO){
        try {
            Principal principal = context.getUserPrincipal();
            String username = principal.getName();
            Kweet kweet = new Kweet(kweetDTO);

            kweetService.postKweet(username, kweet);

            KweetDTO returnDto = new KweetDTO(kweet);

            kweetServer.spreadKweet(returnDto);

            return Response.ok().entity(returnDto).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
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

    @GET
    @Path("getAllKweets/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<KweetDTO> getAllKweets(@PathParam("username")String username){
        User currentUser = userService.getUserByName(username);

        List<KweetDTO> userKweetDtoList = new ArrayList<>();
        for (Kweet kweet : kweetService.getAllUserKweets(currentUser)){
            userKweetDtoList.add(new KweetDTO(kweet));
        }

        return userKweetDtoList;
    }

    @GET
    @Path("getDashboard")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<KweetDTO> getDashboard(@QueryParam("resultPage") int resultPage, @QueryParam("resultSize") int resultSize){
        Principal principal = context.getUserPrincipal();
        User currentUser = userService.getUserByName(principal.getName());

        List<KweetDTO> userDashboard = new ArrayList<>();
        for (Kweet kweet : kweetService.getDashboard(currentUser, resultPage, resultSize)){
            userDashboard.add(new KweetDTO(kweet));
        }

        return userDashboard;
    }

    @GET
    @Path("search/{searchQuery}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<KweetDTO> search(@PathParam("searchQuery") String searchQuery, @QueryParam("resultPage") int resultPage, @QueryParam("resultSize") int resultSize){
        List<KweetDTO> searchResult = new ArrayList<>();
        for (Kweet kweet : kweetService.getSearchResult(searchQuery, resultPage, resultSize)){
            searchResult.add(new KweetDTO(kweet));
        }

        return searchResult;
    }
}
