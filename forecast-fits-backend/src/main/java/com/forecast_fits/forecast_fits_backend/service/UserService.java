package com.forecast_fits.forecast_fits_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.forecast_fits.forecast_fits_backend.model.entity.User;
import com.forecast_fits.forecast_fits_backend.repository.UserRepository;

import jakarta.validation.constraints.NotNull;

import com.forecast_fits.forecast_fits_backend.model.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User>getById(@NotNull int id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public void addUser(UserDTO newUser) {
        User user = new User(newUser.getUserName(), newUser.getPassword(), newUser.getEmail());
        userRepository.save(user);
    }
}
