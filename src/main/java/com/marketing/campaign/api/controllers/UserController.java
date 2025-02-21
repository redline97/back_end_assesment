package com.marketing.campaign.api.controllers;

import com.marketing.campaign.api.domain.UserDTO;
import com.marketing.campaign.api.model.UserEntity;
import com.marketing.campaign.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/mc/v1/users")
    public ResponseEntity<List<UserEntity>> listUser() {
        return userService.listUser();
    }

    @PostMapping("/api/mc/v1/users/save")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) {

        ResponseEntity<String> BAD_REQUEST = validateUserDetails(user);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        try {
            return userService.createUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/mc/v1/users/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId,
                                             @RequestBody UserDTO user) {

        ResponseEntity<String> BAD_REQUEST = validateUserIdAndDetails(userId, user);
        if (BAD_REQUEST != null) return BAD_REQUEST;

        try {
            return userService.updateUser(userId, user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/mc/v1/users/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {

        ResponseEntity<String> BAD_REQUEST = validateUserId(userId);
        if (BAD_REQUEST != null) return BAD_REQUEST;


        try {
            return userService.deleteUser(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static ResponseEntity<String> validateUserDetails(UserDTO user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            return new ResponseEntity<>("Missing user name", HttpStatus.BAD_REQUEST);
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return new ResponseEntity<>("Missing user email", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private static ResponseEntity<String> validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            return new ResponseEntity<>("Invalid user Id", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private static ResponseEntity<String> validateUserIdAndDetails(Long userId, UserDTO userDetails) {
        ResponseEntity<String> responseEntity;
        responseEntity = validateUserDetails(userDetails);
        if (responseEntity != null) {
            return responseEntity;
        } else {
            responseEntity = validateUserId(userId);
        }
        return responseEntity;
    }


}
