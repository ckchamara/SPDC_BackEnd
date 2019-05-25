package com.example.sliit.spdc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sliit.spdc.entities.User;

@Service("userService")
@Transactional
public interface UserService {

	User findByUid(String uid);

    User findByEmail(String email);

    long getUidOfEmail(String email);

    String getEmailOfUid(long uid);

    List<User> findUsersHavingName(String name);

    void saveUser(User user);

    void updateUser(long uid, User userUpdate);

    void deleteUserByEmail(String email);

    List<User> findAllUsers();

    boolean isUserExist(long uid);

    boolean isPasswordCorrect(String email, long password);



}
