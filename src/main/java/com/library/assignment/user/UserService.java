package com.library.assignment.user;


import com.library.assignment.book.Book;
import com.library.assignment.book.BookRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepo userRepo;
    BookRepo bookRepo;


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
            User user = optionalUserToUpdate.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "email":
                        user.setEmail((String) value);
                        break;
                    case "firstName":
                        user.setFirstName((String) value);
                        break;
                    case "lastName":
                        user.setLastName((String) value);
                        break;
                    case "pinCode":
                        user.setPinCode((String) value);
                        break;
                }
            });
        userRepo.save(user);
        return ResponseEntity.ok(user);
    }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<Book>> getBooksByUser(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()){
           List<Book> books = bookRepo.findByBorrowerId(userId);
           if (books!=null){
               return  ResponseEntity.ok(books);
           } else {
               return ResponseEntity.noContent().build();
           }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<UserDto> login(Map<String, String> credentials) {
        String email = credentials.get("email");
        String pinCode = credentials.get("pinCode");
        System.out.println(credentials);


        Optional<User> optionalUser = userRepo.findByEmail(email);
        System.out.println("OptinalUser: "+ optionalUser);
        if (optionalUser.isPresent() && optionalUser.get().getPinCode().equals(pinCode)){
            User userToLogIn = optionalUser.get();
            UserDto userToSendBack = new UserDto(userToLogIn.getFirstName(), userToLogIn.getEmail(), userToLogIn.getUserId(), userToLogIn.getUserType());
            System.out.println(userToSendBack.getId());
            return ResponseEntity.ok(userToSendBack);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
