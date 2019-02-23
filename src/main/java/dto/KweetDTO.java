package dto;

import model.Kweet;

public class KweetDTO {

    private int id;
    private String message;

    public KweetDTO(){}

    public KweetDTO(Kweet kweet){
        this.id = kweet.getId();
        this.message = kweet.getMessage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
