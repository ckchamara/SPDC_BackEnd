package com.example.sliit.spdc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sliit.spdc.entities.Flower;

@Service("flowerService")
@Transactional
public interface FlowerSendingService {
	Flower findById(String tId);

	Flower findByName(String name);

    List<Flower> findCustomerName(String name);

    Optional<Flower> findSendingDate(String date);

    void saveFlower(Flower flower);

    void updateFlower(Flower flowerUpdate);

    void deleteFlowerById(String tId);

    List<Flower> findAllFlower();

    public boolean isFoodExist(Flower flower);
}
