package com.library.assignment.user;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepo userRepo;


    public ResponseEntity<User> findUserById(Long userId) {
        Optional<User> wantedUser = userRepo.findById(userId);
        if (wantedUser.isPresent()){
            return ResponseEntity.ok(wantedUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    public ResponseEntity<Iterable<User>> getAllUsers() {
        Optional<List<User>> userListOptional = Optional.of(userRepo.findAll());
        if (userListOptional.isPresent()){
            return ResponseEntity.ok(userListOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucb) {

        userRepo.save(user);
        URI locationOfNewUser = ucb
                .path("api/v1/users/{id}")
                .buildAndExpand(user.getUserId())
                .toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }
}
