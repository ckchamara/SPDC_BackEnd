package com.example.sliit.spdc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.sliit.spdc.entities.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session, String> {

    Session findByUid(String uid);
    Session findByAuthKeyOfUid(String authKey);
    List<Session> findByRole(String role);

    void deleteByAuthKeyOfUid(String authKey);
    void deleteByUid(String uid);
}
