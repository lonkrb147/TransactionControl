package com.transactioncontrol.dao;

import com.transactioncontrol.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByUsername(String username);
}
