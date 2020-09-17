package com.example.demo.controller;

import com.example.demo.form.UserForm;
import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Random;
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

    @PostMapping
    ResponseEntity<User> create(@RequestBody UserForm userForm) {
        //przekazanie do serwisu i utworzenie uzytkownika
        User newUser = new User(new Random().nextLong(), userForm.getFirstName(), userForm.getLastName());
        URI uri = UriComponentsBuilder.fromPath("/api/users/{id}")
                .build(newUser.getId());

        return ResponseEntity.created(uri).body(newUser);
    }

    @PutMapping("/{id}")
    void update(@PathVariable Long id, @RequestBody UserForm userForm) {
        //edycja
        //zapytać bazę danych czy dany użytkownik istnieje
        //jeżeli istnieje to wyciągnać go z bazy danych zupdatować mu dane i zapisać do bazy
        //jeżeli nie istnieje to stworzyć nowego uzytkownika
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        //usunięcie
    }
}
