package rest;

import dto.UserDTO;
import model.User;
import security.JWTTokenNeeded;
import services.UserService;

import javax.inject.Inject;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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

    @POST
    @Path("addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(UserDTO userDTO){
        User user = new User(userDTO);
        userService.addUser(user);
    }

    @GET
    @Path("getUserByName")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public UserDTO getUserByName(){
        UserDTO userDTO = new UserDTO(userService.getUserByName("TestUser"));
        return userDTO;
    }

    @POST
    @Path("editUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public void editUser(UserDTO userDTO){
        User user = new User(userDTO);
        userService.editUser(user);
    }

    @POST
    @Path("followUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public void followUser(UserDTO userDTO){
        Principal principal = context.getUserPrincipal();
        User currentUser = userService.getUserByName(principal.getName());
        User userToBeFollowed = userService.getUserByName(userDTO.getUsername());

        userService.followUser(currentUser, userToBeFollowed);
    }

    @POST
    @Path("editUserRole")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editUserRole(UserDTO userDTO){
        User user = new User(userDTO);
        userService.editUser(user);
    }

    @GET
    @Path("getFollowing")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<UserDTO> getAllFollowing(){
        Principal principal = context.getUserPrincipal();
        User currentUser = userService.getUserByName(principal.getName());

        List<UserDTO> usersFollowingDtoList = new ArrayList<>();
        for (User user : userService.getAllFollowing(currentUser)){
            usersFollowingDtoList.add(new UserDTO(user));
        }

        return usersFollowingDtoList;
    }

    @GET
    @Path("getFollowers")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<UserDTO> getAllFollowers(){
        Principal principal = context.getUserPrincipal();
        User currentUser = userService.getUserByName(principal.getName());

        List<UserDTO> usersFollowerDtoList = new ArrayList<>();
        for (User user : userService.getAllFollowers(currentUser)){
            usersFollowerDtoList.add(new UserDTO(user));
        }

        return usersFollowerDtoList;
    }
}
