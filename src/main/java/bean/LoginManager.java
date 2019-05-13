package bean;

import callbackhandler.LoginCallBackHandler;
import dao.UserDAO;
import dao.UserDAOImpl;
import dto.RegisterDTO;
import dto.UserDTO;
import model.User;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;

@Model
@RequestScoped
public class LoginManager {

    @EJB
    private UserService userService;

    @Produces
    @Named
    private RegisterDTO currentUser;

    @PostConstruct
    void initUserDTO(){
        currentUser = new RegisterDTO();
    }

    public void login(){
        try {
            LoginCallBackHandler loginCallBackHandler = new LoginCallBackHandler(currentUser);
            LoginContext lc = new LoginContext("jboss-security-api", loginCallBackHandler);
            FacesContext context = FacesContext.getCurrentInstance();
            lc.login();
            context.getExternalContext().redirect("userOverview.xhtml");
            context.getExternalContext().getSessionMap().put("user", currentUser.getUsername());
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
