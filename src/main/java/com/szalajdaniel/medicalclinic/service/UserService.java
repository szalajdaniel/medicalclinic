package com.szalajdaniel.medicalclinic.service;

import com.szalajdaniel.medicalclinic.mapper.UserMapper;
import com.szalajdaniel.medicalclinic.model.dto.CreateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UpdateUserCommandDto;
import com.szalajdaniel.medicalclinic.model.dto.UserDto;
import com.szalajdaniel.medicalclinic.model.entity.User;
import com.szalajdaniel.medicalclinic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<UserDto> getAllPatients(){
        return repository.getUsers().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserDto addUser(CreateUserCommandDto createUserCommandDto){
        repository.findByUsername(createUserCommandDto.username())
                .ifPresent(user -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Istnieje już użytkownik o podanym username"
                    );
                });
        User user = UserMapper.toUser(createUserCommandDto);
        repository.addUser(user);
        return UserMapper.toDto(user);
    }

    public void deleteUserByUsername(String username){
        repository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nie znaleziono użytkownika o podanym username"
                ));
        repository.deleteUser(username);
    }

    public UserDto updateUserByUsername(String username, UpdateUserCommandDto updateUserCommandDto){
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nie znaleziono uzytkownika o podanym username"
                ));
        UserMapper.updateUser(user, updateUserCommandDto);
        return UserMapper.toDto(user);
    }
}
