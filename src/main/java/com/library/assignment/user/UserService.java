package com.library.assignment.user;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
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



    public ResponseEntity<List<User>> getAllUsers() {
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

    public ResponseEntity<Void> deleteUser(Long id) {
        Optional<User> optionalUserToDelete = userRepo.findById(id);
        if (optionalUserToDelete.isPresent()){
            userRepo.delete(optionalUserToDelete.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> updateUser(Long userId, Map<String, Object> updates) {
        Optional<User> optionalUserToUpdate = userRepo.findById(userId);
        if (optionalUserToUpdate.isPresent()){
            //User user = optionalUserToUpdate.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "email":
                        optionalUserToUpdate.get().setEmail((String) value);
                        break;
                    case "firstName":
                        optionalUserToUpdate.get().setFirstName((String) value);
                        break;
                    case "lastName":
                        optionalUserToUpdate.get().setLastName((String) value);
                        break;
                    case "pinCode":
                        optionalUserToUpdate.get().setPinCode((String) value);
                        break;
                }
            });
        userRepo.save(optionalUserToUpdate.get());
        return ResponseEntity.ok(optionalUserToUpdate.get());
    }else {
            return ResponseEntity.notFound().build();
        }
    }
}
