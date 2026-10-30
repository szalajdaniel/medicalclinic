package com.szalajdaniel.medicalclinic.controller;

import com.szalajdaniel.medicalclinic.model.dto.CreateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UserDto;
import com.szalajdaniel.medicalclinic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllPatients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto addUser(@RequestBody CreateUserCommandDto createUserCommandDto){
        return  userService.addUser(createUserCommandDto);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username){
        userService.deleteUserByUsername(username);
    }

    @PutMapping("/{username}")
    public UserDto updateUser(@PathVariable String username, @RequestBody UpdateUserCommandDto updateUserCommandDto){
        return userService.updateUserByUsername(username, updateUserCommandDto);
    }
}
