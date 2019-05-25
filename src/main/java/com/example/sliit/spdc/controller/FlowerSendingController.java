package com.example.sliit.spdc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sliit.spdc.config.Config;
import com.example.sliit.spdc.entities.Flower;
import com.example.sliit.spdc.repository.FlowerRepository;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = Config.allowedOrigin)
public class FlowerSendingController {

	@Autowired
	private FlowerRepository flowerRepository;

	// Retrieve operation
	@RequestMapping(method=RequestMethod.GET, value="/flowers")
    public Iterable<Flower> flower() {
        return flowerRepository.findAll();
    }
	
	// Create operation
    @RequestMapping(method=RequestMethod.POST, value="/flowers")
    public Flower save(@RequestBody Flower flower) {
    	flowerRepository.save(flower);

        return flower;
    }

    @RequestMapping(method=RequestMethod.GET, value="/flowers/{id}")
    public Flower show(@PathVariable String id) {
        return flowerRepository.findByFlowerId(id);
    }

    // Update operation
    @RequestMapping(method=RequestMethod.PUT, value="/flowers/{id}")
    public Flower update(@PathVariable String id, @RequestBody Flower flower) {
       Flower optflower = flowerRepository.findByFlowerId(id);
        Flower t = optflower;
        if(flower.getCustomerName() != null)
            t.setCustomerName(flower.getCustomerName());
        if(flower.getSendingDate() != null)
            t.setSendingDate(flower.getSendingDate());
        if(flower.getSourceStation() != null)
            t.setSourceStation(flower.getSourceStation());
        if(flower.getDestStation() != null)
            t.setDestStation(flower.getDestStation());
        if(flower.getEmail() != null)
            t.setEmail(flower.getEmail());
        flowerRepository.save(t);
        return t;
    }

    // Delete operation
    @RequestMapping(method=RequestMethod.DELETE, value="/flowers/{id}")
    public String delete(@PathVariable String id) {
        //Flower optflower = flowerRepository.findByFlowerId(id);
        //flowerRepository.delete(optflower);

        return "";
    }
}
