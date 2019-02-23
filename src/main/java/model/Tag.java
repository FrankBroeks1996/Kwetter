package model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TAG")
public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    private List<Kweet> kweetsWithTag;

    public Tag(){
        //Empty constructor for JPA
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Kweet> getKweetsWithTag() {
        return kweetsWithTag;
    }

    public void setKweetsWithTag(List<Kweet> kweetsWithTag) {
        this.kweetsWithTag = kweetsWithTag;
    }
}
