package com.example.restfulwebservices.user;

import com.example.restfulwebservices.user.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.cglib.core.Local;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User {
    protected User(){};
    @Id
    @GeneratedValue
    private Integer Id;

    @Size(min=2 , message = "Name should has at least 2 characters")
//    @JsonProperty("user_name")
    private  String name;
    @Past(message = "Birth Date should be in the past")
//    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public User(Integer id , String name, LocalDate birthDate){
        super();
        this.Id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "[ id = " + Id + " ,name = " + name + " ,birthDate = " + birthDate + " ]";
    }


}
