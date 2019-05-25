package com.example.sliit.spdc.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sliit.spdc.entities.Payment;

@Service("paymentService")
@Transactional
public interface PaymentService {

	Payment findByPid(String pid);

	List<Payment> findByUid(String uid);

	List<Payment> findByPaymentDate(Date date);

	List<Payment> getAllPayments();

    void savePayment(Payment payment);

    void updatePayment(String pid, Payment paymentUpdate);

    void deletePayment(Payment payment);

    boolean isPaymentExist(Payment payment);
}
