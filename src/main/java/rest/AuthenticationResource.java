package rest;

import dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.User;
import security.KeyGenerator;
import services.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("authentication")
public class AuthenticationResource {

    @Inject
    KeyGenerator keyGenerator;

    @Inject
    private UserService userService;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO){
        try{
            User user = new User(userDTO);
            if(userService.login(user)) {
                String token = issueToken(userDTO.getUsername());

                return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
            }else{
                return Response.status(UNAUTHORIZED).build();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String issueToken(String username){
        Key key = keyGenerator.generateKey();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date newDate = calendar.getTime();

        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(newDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }
}
