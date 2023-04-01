package com.example.restfulwebservices.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import net.minidev.json.annotate.JsonIgnore;

@Entity
public class Post {
    protected Post(){};
    @Id
    @GeneratedValue
    private Integer Id;

    @Size(min = 5)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post [id=" + Id + ", description=" + description + "]";
    }


}
