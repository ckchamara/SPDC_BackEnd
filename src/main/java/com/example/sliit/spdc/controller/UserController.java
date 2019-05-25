package com.example.sliit.spdc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.sliit.spdc.entities.User;
import com.example.sliit.spdc.repository.UserRepository;
import com.example.sliit.spdc.config.Config;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = Config.allowedOrigin)
public class UserController {
	@Autowired
    UserRepository userRepository;

	// Retrieve operation
    @RequestMapping(method=RequestMethod.GET, value="/users")
    public Iterable<User> user() {
        return userRepository.findAll();
    }

    // Insert operation
    @RequestMapping(method=RequestMethod.POST, value="/users")
    public User save(@RequestBody User user) {
    	userRepository.save(user);

        return user;
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}")
    public Optional<User> show(@PathVariable String id) {
        return userRepository.findById(id);
    }

    // Update operation
    @RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        Optional<User> optcontact = userRepository.findById(id);
        User u = optcontact.get();
        if(user.getName() != null)
            u.setName(user.getName());
        if(user.getEmail() != null)
            u.setEmail(user.getEmail());
        if(user.getPassword() != null)
            u.setPassword(user.getPassword());
        if(user.getCpasswrd() != null)
            u.setCpasswrd(user.getCpasswrd());
        if(user.getNic() != null)
            u.setNic(user.getNic());
        userRepository.save(u);
        return u;
    }

    // Delete operation
    @RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
    public String delete(@PathVariable String id) {
        Optional<User> optuser = userRepository.findById(id);
        User user = optuser.get();
        userRepository.delete(user);

        return "";
    }
}