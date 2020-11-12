package com.acme.petcompanion.service;

import com.acme.petcompanion.domain.model.UserData;
import com.acme.petcompanion.domain.repository.UserDataRepository;
import com.acme.petcompanion.domain.repository.UserRepository;
import com.acme.petcompanion.domain.service.UserDataService;
import com.acme.petcompanion.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserData> getAllUserData (Pageable pageable){
        return userDataRepository.findAll(pageable);
    }

    @Override
    public UserData getUserDataByUserId (Long userId){
        return userDataRepository.findByUserId(userId);
    }

    @Override
    public  UserData createUserData (Long userId, UserData userData){
        return userRepository.findById(userId).map(user -> {
            userData.setUser(user);
            return userDataRepository.save(userData);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId));
    }

    @Override
    public UserData updateUserData (Long userId,Long userDataId, UserData userDataRequest){
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return userDataRepository.findById(userDataId).map(userData -> {
            userData.setName(userDataRequest.getName());
            userData.setLastName(userDataRequest.getLastName());
            userData.setScoreOwner(userDataRequest.getScoreOwner());
            userData.setScoreProvider(userDataRequest.getScoreProvider());
            return userDataRepository.save(userData);
        }).orElseThrow(() -> new ResourceNotFoundException("User Data", "Id", userDataId));

    }

    @Override
    public ResponseEntity<?> deleteUserData(Long userId, Long userDataId){
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return userDataRepository.findById(userDataId).map(userData -> {
            userDataRepository.delete(userData);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User Data", "Id", userDataId));
    }
}
