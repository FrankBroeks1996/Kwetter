package bean;

import model.Role;
import model.User;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Model
@RequestScoped
public class OverviewManager {

    @EJB
    private UserService userService;

    @Produces
    @Named
    private List<User> users;

    @PostConstruct
    void init(){
        users = userService.getAllUsers();
    }

    public SelectItem[] getRoleValues() {
        SelectItem[] items = new SelectItem[Role.values().length];
        int i = 0;
        for(Role r: Role.values()) {
            items[i++] = new SelectItem(r);
        }
        return items;
    }

    public void updateRole(User user){
        userService.editUser(user);
    }

    public void goToKweetOverview(User user){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().getSessionMap().put("selectedUser", user.getUsername());
            context.getExternalContext().redirect("kweetOverview.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
