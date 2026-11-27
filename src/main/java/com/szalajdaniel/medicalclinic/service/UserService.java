package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.exception.UserAlreadyAssignedException;
import com.szalajdaniel.medicalclinic.mapper.UserMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UserDto;
import com.szalajdaniel.medicalclinic.model.entity.User;
import com.szalajdaniel.medicalclinic.repository.UserRepository;
import com.szalajdaniel.medicalclinic.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto addUser(CreateUserCommandDto createUserCommandDto) {
        userRepository.findByUsername(createUserCommandDto.username())
                .ifPresent(user -> { throw new UserAlreadyAssignedException("User with username " + createUserCommandDto.username() + " already exists");});
        User user = userMapper.toUser(createUserCommandDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUserByUsername(String username) {
        User user = userValidator.validateUserNotExistByUsername(username);
        userRepository.delete(user);
    }

    @Transactional
    public UserDto updateUserByUsername(String username, UpdateUserCommandDto updateUserCommandDto) {
        User user = userValidator.validateUserNotExistByUsername(username);
        user.update(updateUserCommandDto);
        return userMapper.toDto(user);
    }
}
