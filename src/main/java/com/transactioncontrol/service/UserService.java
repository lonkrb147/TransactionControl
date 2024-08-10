package com.transactioncontrol.service;

import com.transactioncontrol.dao.UserDao;
import com.transactioncontrol.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
