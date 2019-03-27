package bean;

import model.Kweet;
import model.User;
import services.KweetService;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;

@Model
@SessionScoped
public class KweetOverviewManager {

    @EJB
    UserService userService;

    @EJB
    KweetService kweetService;

    @Named
    User user;

    @Produces
    @Named
    List<Kweet> kweetList;

    @PostConstruct
    void init(){
        FacesContext context = FacesContext.getCurrentInstance();

        user = userService.getUserByName(context.getExternalContext().getSessionMap().get("selectedUser").toString());
        kweetList = kweetService.getAllKweets(user);
    }

    public void deleteKweet(Kweet kweet){
        kweetService.removeKweet(kweet);
    }
}
