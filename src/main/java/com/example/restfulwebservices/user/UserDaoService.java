package com.example.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCounts = 0;

    static {
        users.add(new User(++userCounts, "adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCounts, "reham", LocalDate.now().minusYears(25)));
        users.add(new User(++userCounts, "nour", LocalDate.now().minusYears(20)));
    }

    //Find all users
    public  List<User> findAll() {
        return users;
    }

    //GET ONE USER
    public  User findOne(Integer id) {
        Predicate<User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    //POST USER
    public User save(User user){
        user.setId(++userCounts);
//        user.setBirthDate(LocalDate.now());
        users.add(user);
        return user;
    }
}
