package com.example.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
public class UserResource {

   private UserDaoService service;

   public UserResource(UserDaoService userDaoService){
       this.service  = userDaoService;
   }

    //GET ALL USERS
    @GetMapping("/users")
    public List<User> retrieveAllUsers () {
      return  service.findAll();
    }

    //GET USER BY ID
    @GetMapping("/users/{id}")
    public User retrieveAllUsers (@PathVariable Integer id) {
        User user = service.findOne(id);
        if( user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        return user;
    }

    //DELETE USER BY ID
    @DeleteMapping("/users/{id}")
    public void deleteUser (@PathVariable Integer id) {
         service.deleteById(id);
    }

    //POST USER
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        //   /users/4 -  /users/{id} - user.getId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
