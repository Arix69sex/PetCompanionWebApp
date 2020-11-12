package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.User;
import com.acme.petcompanion.domain.model.UserData;
import com.acme.petcompanion.domain.service.UserDataService;
import com.acme.petcompanion.domain.service.UserService;
import com.acme.petcompanion.resource.SaveUserDataResource;
import com.acme.petcompanion.resource.SaveUserResource;
import com.acme.petcompanion.resource.UserDataResource;
import com.acme.petcompanion.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserDataController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get All UserData", description = "Get All User Data", tags = {"userData"})
    @GetMapping("/users/userData")
    public Page<UserDataResource> getAllUserData(Pageable pageable){
        Page<UserData> userDataPage = userDataService.getAllUserData(pageable);
        List<UserDataResource> resources = userDataPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get UserData by UserId", description = "Get UserData by its Userid", tags = {"userData"})
    @GetMapping("/users/{userId}/userData")
    public UserDataResource getUserDataByUserId(@PathVariable Long userId){
        return convertToResource(userDataService.getUserDataByUserId(userId));
    }

    @Operation(summary = "Create User Data", description = "Create User Data of a User", tags = {"userData"})
    @PostMapping("/users/{userId}/userData")
    public UserDataResource createUserData(@Valid @RequestBody SaveUserDataResource resource, @PathVariable Long userId){
        UserData userData = convertToEntity(resource);
        return convertToResource(userDataService.createUserData(userId, userData));
    }

    @Operation(summary = "Update a UserData", description = "Update an existing UserData with given UserId", tags = {"user"})
    @PutMapping("/users/{userId}/userData")
    public UserDataResource updateUserData(@PathVariable Long userId, @RequestBody SaveUserDataResource resource) {
        UserData userData = convertToEntity(resource);
        return convertToResource(userDataService.updateUserData(userId, userData.getId(), userData));
    }

    @Operation(summary = "Delete a UserData", description = "Delete an existing UserData with given Id", tags = {"userData"})
    @DeleteMapping("/users/{userId}/userData/{userDataId}")
    public ResponseEntity<?> deleteUserData(@PathVariable Long userId, @PathVariable Long userDataId, @RequestBody SaveUserDataResource resource) {
       return userDataService.deleteUserData(userId, userDataId);
    }

    private UserData convertToEntity(SaveUserDataResource resource) {
        return mapper.map(resource, UserData.class);
    }

    private UserDataResource convertToResource(UserData entity){
        return mapper.map(entity, UserDataResource.class);
    }
}
