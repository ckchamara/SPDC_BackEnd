package com.example.sliit.spdc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.sliit.spdc.entities.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String> {

    Payment findByPid(String pid);

    List<Payment> findByPaymentDate(Date date);
    List<Payment> findByUid(String uid);

    void deleteByPid(Payment payment);
}

