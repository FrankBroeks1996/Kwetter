package rest;

import dto.RegisterDTO;
import dto.UserDTO;
import model.User;
import security.JWTTokenNeeded;
import services.UserService;

import javax.inject.Inject;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Path("user")
public class UserResource {

    @Inject
    UserService userService;

    @Context
    SecurityContext context;

    public UserResource(){}

    @GET
    @Path("getUserByName/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public UserDTO getUserByName(@PathParam("username") String username){
        UserDTO userDTO = new UserDTO(userService.getUserByName(username));
        return userDTO;
    }

    @PATCH
    @Path("editUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public Response editUser(UserDTO userDTO){
        try {
            User user = userService.getUserByName(userDTO.getUsername());
            user.setBio(userDTO.getBio());
            user.setLocation(userDTO.getLocation());
            user.setWebsite(userDTO.getWebsite());
            userService.editUser(user);
            userDTO = new UserDTO(user);
            return Response.ok().entity(userDTO).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Path("followUser/{username}")
    @JWTTokenNeeded
    public void followUser(@PathParam("username") String username){
        Principal principal = context.getUserPrincipal();
        User currentUser = userService.getUserByName(principal.getName());
        User userToBeFollowed = userService.getUserByName(username);

        userService.followUser(currentUser, userToBeFollowed);
    }

    @POST
    @Path("unFollowUser/{username}")
    @JWTTokenNeeded
    public void unFollowUser(@PathParam("username") String username){
        Principal principal = context.getUserPrincipal();
        User currentUser = userService.getUserByName(principal.getName());
        User userToBeUnfollowed = userService.getUserByName(username);

        userService.unFollowUser(currentUser, userToBeUnfollowed);
    }

    @GET
    @Path("isFollowing/{username}")
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public boolean isFollowing(@PathParam("username") String username){
        Principal principal = context.getUserPrincipal();
        User currentUser = userService.getUserByName(principal.getName());
        User checkUser = userService.getUserByName(username);

        return userService.isFollowing(currentUser, checkUser);
    }

    @POST
    @Path("editUserRole")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editUserRole(UserDTO userDTO){
        User user = new User(userDTO);
        userService.editUser(user);
    }

    @GET
    @Path("getFollowing/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<UserDTO> getAllFollowing(@PathParam("username") String username){
        User currentUser = userService.getUserByName(username);

        List<UserDTO> usersFollowingDtoList = new ArrayList<>();
        for (User user : userService.getAllFollowing(currentUser)){
            usersFollowingDtoList.add(new UserDTO(user));
        }

        return usersFollowingDtoList;
    }

    @GET
    @Path("getFollowers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<UserDTO> getAllFollowers(@PathParam("username") String username){
        User currentUser = userService.getUserByName(username);

        List<UserDTO> usersFollowerDtoList = new ArrayList<>();
        for (User user : userService.getAllFollowers(currentUser)){
            usersFollowerDtoList.add(new UserDTO(user));
        }

        return usersFollowerDtoList;
    }
}
