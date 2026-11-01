package com.szalajdaniel.medicalclinic.repository;

import com.szalajdaniel.medicalclinic.model.entity.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final List<User>  users = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void addUser(User user){
        user.setUserID(idGenerator.getAndIncrement());
        users.add(user);
    }

    public void deleteUser(String username){
        users.removeIf(user -> user.getUsername().equals(username));
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
