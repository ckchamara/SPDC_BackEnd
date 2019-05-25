package com.example.sliit.spdc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sliit.spdc.entities.Flower;
import com.example.sliit.spdc.repository.FlowerRepository;

@Service("flowerService")
@Transactional
public class FlowerServiceImpl implements FlowerSendingService{

	
	@Autowired
    private FlowerRepository flowerRepository;

    private static List<Flower> flower;
    
	@Override
	public Flower findById(String tId) {
		return flowerRepository.findByFlowerId(tId);
	}

	@Override
	public Flower findByName(String name) {
		return flowerRepository.findByCustomerName(name);
	}

	@Override
	public List<Flower> findCustomerName(String name) {
		Iterable<Flower> foodIterable = (Iterable<Flower>) flowerRepository.findByCustomerName(name);
        List<Flower> foodList = new ArrayList<>();

        foodIterable.forEach(foodList::add);
        return foodList;
	}

	@Override
	public Optional<Flower> findSendingDate(String date) {
		Optional<Flower> foodIterable = flowerRepository.findBySendingDate(date);
        return foodIterable;
	}

	@Override
	public void saveFlower(Flower flower) {
		flowerRepository.save(flower);
		
	}

	@Override
	public void updateFlower(Flower flowerUpdate) {
		Flower flowerExisting = findById(flowerUpdate.getFlowerId());

        // update the item we retrieved.
        if (flowerExisting != null) {
        	flowerExisting.setSourceStation(flowerUpdate.getSourceStation());
        	flowerExisting.setCustomerName(flowerUpdate.getCustomerName());
        	flowerExisting.setCustomerName(flowerUpdate.getCustomerName());
        	flowerExisting.setDestStation(flowerUpdate.getDestStation());
        	flowerExisting.setEmail(flowerUpdate.getEmail());
            saveFlower(flowerExisting);
        }
		
	}

	@Override
	public void deleteFlowerById(String tId) {
		flowerRepository.findByFlowerId(tId);
		
	}

	@Override
	public List<Flower> findAllFlower() {
		return (List<Flower>) flowerRepository.findAll();
	}

	@Override
	public boolean isFoodExist(Flower flower) {
		return flowerRepository.exists(flower.getFlowerId());
	}

	public double getPriceOf(String tId) {
		Flower flower = new Flower();
		return flower.getPriceOf(tId);
	}

}
