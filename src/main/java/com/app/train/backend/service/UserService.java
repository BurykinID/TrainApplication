package com.app.train.backend.service;

import com.app.train.backend.entity.User;
import com.app.train.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByName(String username) {
        if (username.isEmpty()) {
            return new User();
        }
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(User user) {

        if (user == null) {
            return new User();
        }

        return userRepository.findById(user.getId()).orElse(new User());

    }

}
