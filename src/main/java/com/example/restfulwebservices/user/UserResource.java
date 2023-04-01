package com.example.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResource {

   private UserDaoService service;

   private MessageSource messageSource;

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
    public EntityModel<User> retrieveUser (@PathVariable Integer id) {
        User user = service.findOne(id);
        if( user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
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
