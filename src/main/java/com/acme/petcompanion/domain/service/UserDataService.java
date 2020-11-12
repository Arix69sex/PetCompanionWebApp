package com.acme.petcompanion.domain.service;

import com.acme.petcompanion.domain.model.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserDataService {

    Page<UserData> getAllUserData (Pageable pageable);

    UserData getUserDataByUserId (Long userId);

    UserData createUserData (Long userId,UserData userData);

    UserData updateUserData (Long userId,Long userDataId, UserData userDataRequest);

    ResponseEntity<?> deleteUserData(Long userId, Long userDataId);
}
