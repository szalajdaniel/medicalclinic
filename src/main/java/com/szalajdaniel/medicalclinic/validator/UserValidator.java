package com.szalajdaniel.medicalclinic.validator;

import com.szalajdaniel.medicalclinic.exception.NotFoundUserException;
import com.szalajdaniel.medicalclinic.exception.PatientAlreadyExistsException;
import com.szalajdaniel.medicalclinic.model.entity.User;
import com.szalajdaniel.medicalclinic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public User validateUserNotExistByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundUserException("User with username " + username + " not found"));
    }
}
