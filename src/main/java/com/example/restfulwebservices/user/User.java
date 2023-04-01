package com.example.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class User {
    private Integer Id;

    @Size(min=2 , message = "Name should has at least 2 characters")
    @JsonProperty("user_name")
    private  String name;
    @Past(message = "Birth Date should be in the past")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

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

    @Override
    public String toString() {
        return "[ id = " + Id + " ,name = " + name + " ,birthDate = " + birthDate + " ]";
    }
}
