package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dto.UserDTO;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.*;
import javax.ws.rs.Consumes;
import java.util.*;

@Entity
@Table(name = "USER")
@NamedQueries({
    @NamedQuery(name = "User.getUserByName", query = "SELECT u FROM User u WHERE u.username = :name"),
    @NamedQuery(name = "User.getAllUsers", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.login", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
    @NamedQuery(name = "User.isFollowing", query = "SELECT u FROM User u WHERE u.username = :username AND :checkUser MEMBER OF u.following")
})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "location")
    private String location;

    @Column(name = "website")
    private String website;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profilePicturePath")
    private String profilePicturePath;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Kweet> kweets = new ArrayList<>();

    @ManyToMany(mappedBy = "mentions")
    private List<Kweet> mentionedIn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "following", joinColumns = @JoinColumn(name = "following_id"), inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> following = new HashSet<>();

    public User() {
        //JPA empty constructor
    }

    public User(UserDTO userDTO){
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        //this.password = userDTO.getPassword();
        this.location = userDTO.getLocation();
        this.website = userDTO.getWebsite();
        this.bio = userDTO.getBio();
        this.profilePicturePath = userDTO.getProfilePicturePath();
        this.role = userDTO.getRole();
    }

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

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

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public List<Kweet> getMentionedIn() {
        return mentionedIn;
    }

    public void setMentionedIn(List<Kweet> mentionedIn) {
        this.mentionedIn = mentionedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}