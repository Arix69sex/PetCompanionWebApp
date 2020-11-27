package com.acme.petcompanion.service;

import com.acme.petcompanion.domain.model.User;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.UserService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import library.ObserverPattern.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private Reminder reminder;

    @Override
    public Page<User> getAllUsers (Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById (Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
    }

    @Override
    public User createUser (User user){
        return userRepository.save(user);
    }

    @Override
    public User updateUser (Long userId, User userRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPremium(userRequest.isPremium());
        if(userRequest.isPremium()) reminder.addPremiumUser(user);
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
    }
}
