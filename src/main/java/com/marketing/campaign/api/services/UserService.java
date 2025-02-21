package com.marketing.campaign.api.services;


import com.marketing.campaign.api.domain.UserDTO;
import com.marketing.campaign.api.model.UserEntity;
import com.marketing.campaign.api.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(
            value = "transactionManager",
            rollbackFor = {Exception.class},
            readOnly = true
    )
    public ResponseEntity<List<UserEntity>> listUser() {
        List<UserEntity> userEntity = userRepository.findAll();
        return ResponseEntity.ok(userEntity);
    }

    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<String> createUser(UserDTO userDetails) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDetails.getName());
        userEntity.setUserEmail(userDetails.getEmail());
        userRepository.save(userEntity);
        return ResponseEntity.ok("User created successfully!!");
    }

    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<String> updateUser(Long userId, UserDTO userDetails) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }
        user.setUsername(userDetails.getName());
        user.setUserEmail(userDetails.getEmail());
        userRepository.save(user);
        return ResponseEntity.ok("User updated successfully!!");
    }

    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<String> deleteUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully!!");
    }
}
