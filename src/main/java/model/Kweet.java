package model;

import dto.KweetDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "KWEET")
@NamedQueries({
        @NamedQuery(name = "Kweet.getKweetById", query = "SELECT k FROM Kweet k WHERE k.id = :id"),
        @NamedQuery(name = "Kweet.getAllUserKweets", query = "SELECT k FROM Kweet k WHERE k.author = :user ORDER BY k.createdAt DESC"),
        @NamedQuery(name = "Kweet.getDashboard", query = "SELECT k FROM Kweet k WHERE :currentUser = k.author OR :currentUser MEMBER OF k.author.followers ORDER BY k.createdAt DESC"),
        @NamedQuery(name = "Kweet.searchKweets", query = "SELECT k FROM Kweet k WHERE k.message LIKE :searchQuery ORDER BY k.createdAt DESC"),
})
public class Kweet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message")
    private String message;

    @ManyToMany
    @JoinTable(name = "hearts", joinColumns = @JoinColumn(name = "kweet_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> hearts = new ArrayList<>();

    @ManyToMany
    private List<User> mentions;

    @ManyToOne
    private User author;

    @ManyToMany
    private List<Tag> tags;

    @CreationTimestamp
    private Date createdAt;

    public Kweet(){
        //Empty constructor for JPA
    }

    public Kweet(KweetDTO kweetDTO){
        this.id = kweetDTO.getId();
        this.message = kweetDTO.getMessage();
    }

    public int getId() {
        return id;
    }

    public void  setId(int id){this.id = id;}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getHearts() {
        return hearts;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setHearts(List<User> hearts) {
        this.hearts = hearts;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

//    public void addHeart(User user){
//        this.hearts.add(user);
//    }
}

