package com.library.assignment.user;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    UserService userService;

    @GetMapping
    private ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("{id}")
    private ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.findUserById(id);
    }
    @PostMapping
    private ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucb) {

        return userService.addUser(user, ucb);
    }


}
