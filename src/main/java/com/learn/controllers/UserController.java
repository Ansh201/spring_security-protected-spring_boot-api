package com.learn.controllers;

import com.learn.models.User;
import com.learn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // now service class ke methods use krenge so autowire krna pdega service class ka object chahiye
   @Autowired
    private UserService userService;

   //ALL USERS
  @GetMapping("/")
    public List<User> getAllUsers(){
    return this.userService.getAllUsers();
}
  // Return Single User
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
      return this.userService.getUser(username);
    }
    @PostMapping("/")
    public User add(@RequestBody User user){
      return this.userService.addUser(user);
    }



}
