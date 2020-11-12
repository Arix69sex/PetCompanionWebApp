package com.acme.petcompanion.controller;

import com.acme.petcompanion.domain.model.User;
import com.acme.petcompanion.domain.service.UserService;
import com.acme.petcompanion.resource.SaveUserResource;
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
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get All Users", description = "Get All Users", tags = {"users"})
    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable){
        Page<User> userPage = userService.getAllUsers(pageable);
        List<UserResource> resources = userPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
/*
    @Operation(summary = "Get User by Id", description = "Get User by Id", tags = {"users"})
    @GetMapping("/users/{userId}")
    public UserResource GetUserById(@PathVariable Long userId){
        return convertToResource(userService.getUserById(userId));
    }
*/
    @Operation(summary = "Create a User", description = "Create a new User", tags = {"user"})
    @PostMapping("/users/")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }

    @Operation(summary = "Update a User", description = "Update an existing User with given Id", tags = {"user"})
    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId,user));
    }

    @Operation(summary = "Delete a User", description = "Delete an existing User using its id", tags = {"users"})
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity){
        return mapper.map(entity, UserResource.class);
    }
}
