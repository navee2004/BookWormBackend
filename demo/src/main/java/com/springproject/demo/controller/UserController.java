package com.springproject.demo.controller;

import com.springproject.demo.model.UserModel;
import com.springproject.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel) {
        UserModel newUser = userService.addUser(userModel);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        UserModel userModel = userService.getUserById(id);
        if (userModel != null) {
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel, @PathVariable Long id) {
        UserModel updatedUser = userService.updateUser(userModel, id);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/book/sortBy/{field}")
    public ResponseEntity<?> getchild(@PathVariable String field) {
        try {
            return new ResponseEntity<>(userService.getchild(field), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/book/{offset}/{pagesize}")
    public ResponseEntity<?> gettchild(@PathVariable int offset, @PathVariable int pagesize) {
        try {
            return new ResponseEntity<>(userService.gettchild(offset, pagesize), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/book/{offset}/{pagesize}/{field}")
    public ResponseEntity<?> getttchild(@PathVariable int offset, @PathVariable int pagesize,
            @PathVariable String field) {
        try {
            return new ResponseEntity<>(userService.getttchild(offset, pagesize, field), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserModel> getUserByEmail(@PathVariable String email) {
        UserModel user = userService.findUserByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @PostMapping("/create")
    // public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
    // UserModel createdUser = userService.saveUser(user);
    // return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    // }
}
