package com.example.restfulwebservices.user;

import com.example.restfulwebservices.user.jpa.PostRepository;
import com.example.restfulwebservices.user.jpa.UserRepository;
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
public class UserJpaResource {
    private UserRepository userRepository;

    private PostRepository postRepository;

    private MessageSource messageSource;

    public UserJpaResource( UserRepository repository, PostRepository postRepository){
        this.userRepository = repository;
        this.postRepository = postRepository;
    }

    //GET ALL USERS
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers () {
        return  userRepository.findAll();
    }

    //GET USER BY ID
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser (@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if( user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //DELETE USER BY ID
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser (@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    //POST USER
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        //   /users/4 -  /users/{id} - user.getId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    //CREATE POSTS
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser (@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if( user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        return user.get().getPosts();

    }




}
