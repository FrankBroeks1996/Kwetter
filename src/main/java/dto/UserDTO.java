package dto;

import model.Kweet;
import model.Role;
import model.User;

import java.util.List;

public class UserDTO {

    private int id;

    private String username;

    private String password;

    private String location;

    private String website;

    private String bio;

    private String profilePicturePath;

    private Role role;

    private List<KweetDTO> kweets;

    private List<KweetDTO> mentionedIn;

    public UserDTO(){}

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.location = user.getLocation();
        this.website = user.getWebsite();
        this.bio = user.getBio();
        this.profilePicturePath = user.getProfilePicturePath();
        this.role = user.getRole();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<KweetDTO> getKweets() {
        return kweets;
    }

    public void setKweets(List<KweetDTO> kweets) {
        this.kweets = kweets;
    }

    public List<KweetDTO> getMentionedIn() {
        return mentionedIn;
    }

    public void setMentionedIn(List<KweetDTO> mentionedIn) {
        this.mentionedIn = mentionedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
