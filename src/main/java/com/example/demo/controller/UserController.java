package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    List<User> getAll(@RequestParam(required = false) String firstName) {
        //pobieranie użytkowników z bazy danych
        User userOne = new User(1L, "Jan", "Kowalski");
        User userTwo = new User(2L, "Maciek", "Kowalski");

        if (firstName != null) {
            return Stream.of(userOne, userTwo)
                    .filter(user -> user.getFirstName().equalsIgnoreCase(firstName))
                    .collect(Collectors.toList());
        } else {
            return List.of(userOne, userTwo);
        }
    }

    @GetMapping("/{id}")
    User get(@PathVariable Long id) {
        return new User(id, "Tomasz", "Kowalski");
    }
}
