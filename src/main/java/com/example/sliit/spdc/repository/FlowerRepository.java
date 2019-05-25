package com.example.sliit.spdc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.sliit.spdc.entities.Flower;

@Repository
public interface FlowerRepository extends CrudRepository<Flower, String>{
	@Override
	void delete(Flower deleted);
	Flower findByFlowerId(String flowerId);
	Flower findByCustomerName(String name);
	Optional<Flower> findBySendingDate(String date);
}
