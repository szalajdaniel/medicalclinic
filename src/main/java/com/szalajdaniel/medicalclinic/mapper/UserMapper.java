package com.szalajdaniel.medicalclinic.mapper;


import com.szalajdaniel.medicalclinic.model.dto.CreateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UserDto;
import com.szalajdaniel.medicalclinic.model.entity.User;

public final class UserMapper {

    public static User toUser(CreateUserCommandDto createUserCommandDto){
        User user = new User();
        user.setUsername(createUserCommandDto.username());
        user.setPassword(createUserCommandDto.password());
        return user;
    }

    public static void updateUser(User user, UpdateUserCommandDto updateUserCommandDto){
        user.setUsername(updateUserCommandDto.username());
        user.setPassword(updateUserCommandDto.password());
    }

    public static UserDto toDto(User user){
        return new UserDto(user.getUserID(), user.getUsername());
    }
}
