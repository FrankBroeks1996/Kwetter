package dto;

import model.Tag;

import java.util.List;

public class TagDTO {

    private int id;

    private String name;

    private List<KweetDTO> kweetsWithTag;

    public TagDTO(Tag tag){
        this.id = tag.getId();
        this.name = tag.getName();
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

    public List<KweetDTO> getKweetsWithTag() {
        return kweetsWithTag;
    }

    public void setKweetsWithTag(List<KweetDTO> kweetsWithTag) {
        this.kweetsWithTag = kweetsWithTag;
    }
}
