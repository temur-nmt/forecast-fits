package com.forecast_fits.forecast_fits_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.forecast_fits.forecast_fits_backend.model.entity.User;
import com.forecast_fits.forecast_fits_backend.repository.UserRepository;

import jakarta.validation.constraints.NotNull;

import com.forecast_fits.forecast_fits_backend.model.dto.UserDTO;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User>getById(@NotNull int id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Transactional
    public User addUser(UserDTO newUser) {
        User user = new User(newUser.getUsername(), passwordEncoder.encode(newUser.getPassword()), newUser.getEmail());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> users = userRepository.findByUsername(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " + username + " was not found");
        }

        User userObj = users.get();
        return userObj;
    }
}
