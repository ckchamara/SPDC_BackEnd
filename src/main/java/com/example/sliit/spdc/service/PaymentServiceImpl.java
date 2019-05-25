package com.example.sliit.spdc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sliit.spdc.entities.Payment;
import com.example.sliit.spdc.repository.PaymentRepository;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService{

	@Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment findByPid(String pid) {
        return paymentRepository.findByPid(pid);
    }

    @Override
    public List<Payment> findByUid(String uid) {
        return paymentRepository.findByUid(uid);
    }

    @Override
    public List<Payment> findByPaymentDate(Date date) {
        return paymentRepository.findByPaymentDate(date);
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> l = (List<Payment>) paymentRepository.findAll();
        System.out.println(l.size());
        return (List<Payment>) paymentRepository.findAll();
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Autowired
	@Override
    public void updatePayment(String pid, Payment paymentUpdate) {
        if (pid != paymentUpdate.getPid()) { paymentUpdate.setPid(pid); }

        Payment paymentExisting = findByPid(pid);

        if (paymentExisting != null) {
            paymentExisting.setAmount(paymentUpdate.getAmount());
            paymentExisting.setPaymentDetails(paymentUpdate.getPaymentDetails());
            paymentExisting.setPaymentType(paymentUpdate.getPaymentType());
            paymentExisting.setUid(paymentUpdate.getUid());

            savePayment(paymentExisting);
        }
    }

    @Override
    public void deletePayment(Payment payment) {
    	
    }

    @Override
    public boolean isPaymentExist(Payment payment) {
        return false;
    }
}
