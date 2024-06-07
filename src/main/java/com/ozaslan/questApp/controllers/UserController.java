package com.ozaslan.questApp.controllers;

import com.ozaslan.questApp.entities.User;
import com.ozaslan.questApp.repositories.UserRepository;
import com.ozaslan.questApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

 private UserService userService;

 @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")
    public  User updateOneUser(@PathVariable Long userId,@RequestBody User newUser){
        return userService.updateOneUser(userId,newUser);

    }

    @DeleteMapping("/{userId}")

    public void  deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
