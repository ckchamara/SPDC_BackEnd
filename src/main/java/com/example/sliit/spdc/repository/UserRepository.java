package com.example.sliit.spdc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.sliit.spdc.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
	@Override
	void delete(User deleted);
	Optional<User> findById(String id);
}
