package com.library.assignment.user;


import com.library.assignment.book.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    UserService userService;

    @GetMapping
    private ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("{userId}")
    private ResponseEntity<User> getUser(@PathVariable Long userId){

        return userService.findUserById(userId);
    }
    @PostMapping
    private ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucb) {

        return userService.addUser(user, ucb);
    }
    @DeleteMapping("{userId}")
    private  ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @PatchMapping("{userId}")
    private ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody Map<String, Object> updates){
        return userService.updateUser(userId, updates);
    }
    @GetMapping("{userId}/books")
    private ResponseEntity<List<Book>> getBooksByUser(@PathVariable Long userId){
        return userService.getBooksByUser(userId);
    }


}
