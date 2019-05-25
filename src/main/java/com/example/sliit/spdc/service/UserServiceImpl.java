package com.example.sliit.spdc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sliit.spdc.entities.User;
import com.example.sliit.spdc.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl {

	@Autowired
    private UserRepository userRepository;

    public Optional<User> findById(String uid) {
        return userRepository.findById(uid);
    }

}
