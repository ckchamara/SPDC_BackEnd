package com.example.sliit.spdc.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Flower {

	@Id
	private String flowerId;
	private String customerName;
	private Date SendingDate;
	private String sourceStation;
	private String destStation;
	private String email;
	private double price;
	
	public Flower() {}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFlowerId() {
		return flowerId;
	}
	public void setFlowerId(String flowerId) {
		this.flowerId = flowerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getSendingDate() {
		return SendingDate;
	}
	public void setSendingDate(Date SendingDate) {
		this.SendingDate = SendingDate;
	}
	public String getSourceStation() {
		return sourceStation;
	}
	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}
	public String getDestStation() {
		return destStation;
	}
	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public double getPriceOf(String flowerId){
		return price;
	}
	@Override
	public String toString(){
		return "Flower [flowerId = "+flowerId+", customerName = "+customerName+", SendingDate = "+SendingDate+", sourceStation = "+sourceStation+", destStation = "+destStation+", email = "+email+"]";
	}
}
