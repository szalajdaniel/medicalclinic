package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.CreateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UserDto;
import com.szalajdaniel.medicalclinic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        log.info("GET /users - request received");
        List<UserDto> users = userService.getAllUsers();
        log.info("GET /users - responding with HTTP 200 OK, users returned: {}", users.size());
        return users;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody CreateUserCommandDto createUserCommandDto) {
        log.info("POST /users - request received for username: {}", createUserCommandDto.username());
        UserDto createdUser = userService.addUser(createUserCommandDto);
        log.info("POST /users - responding with HTTP 201 CREATED, username: {}", createUserCommandDto.username());
        return createdUser;
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username) {
        log.info("DELETE /users/{} - request received", username);
        userService.deleteUserByUsername(username);
        log.info("DELETE /users/{} - responding with HTTP 204 NO CONTENT", username);
    }

    @PutMapping("/{username}")
    public UserDto updateUser(@PathVariable String username, @RequestBody UpdateUserCommandDto updateUserCommandDto) {
        log.info("PUT /users/{} - request received", username);
        UserDto updatedUser = userService.updateUserByUsername(username, updateUserCommandDto);
        log.info("PUT /users/{} - responding with HTTP 200 OK", username);
        return updatedUser;
    }
}