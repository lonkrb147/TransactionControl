package com.transactioncontrol.dao;

import com.transactioncontrol.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class JdbcUserDao implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserDao.class);
    private final JdbcTemplate jdbcTemplate;
    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM ONOFFUSER WHERE username = ?";
        try {
            LOGGER.info("SELECT * FROM ONOFFUSER WHERE username = {}", username);
            User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());
            assert user != null;
            LOGGER.info("FOUND USER: {},role: {},status: {}", user.getName(), user.getRule(), user.getStatus());
            return Optional.of(user);
        } catch (Exception e) {
            LOGGER.info("GET USER INFOR ERROR: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setStatus(rs.getString("status"));
            user.setName(rs.getString("name"));
            user.setRule(rs.getString("rule"));
            return user;
        }
    }
}
