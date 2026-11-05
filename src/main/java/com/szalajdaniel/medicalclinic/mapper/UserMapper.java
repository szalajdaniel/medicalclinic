package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.CreateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UserDto;
import com.szalajdaniel.medicalclinic.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserCommandDto createUserCommandDto);
    void updateUser(@MappingTarget User user, UpdateUserCommandDto updateUserCommandDto);
    UserDto toDto(User user);
}