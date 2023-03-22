package com.example.restfulwebservices.user;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class User {
    private Integer Id;
    private  String name;
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
