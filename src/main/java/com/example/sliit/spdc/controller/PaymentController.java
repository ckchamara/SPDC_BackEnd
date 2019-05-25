package com.example.sliit.spdc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.sliit.spdc.entities.Payment;
import com.example.sliit.spdc.payment.connecter.PaymentConnecter;
import com.example.sliit.spdc.service.PaymentServiceImpl;
import com.example.sliit.spdc.service.SessionServiceImpl;
import com.example.sliit.spdc.service.FlowerServiceImpl;

public class PaymentController {
	@Autowired
    private PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Autowired
    private SessionServiceImpl sessionService = new SessionServiceImpl();

    @Autowired
    private FlowerServiceImpl flowerService = new FlowerServiceImpl();

    private PaymentConnecter paymentConnecter = new PaymentConnecter();


    // GET all payment entries in the database.
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> getAllPaymentEntries() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    // GET payment by its payment id.
    @RequestMapping(value = "/pid/{pid}", method = RequestMethod.GET)
    public ResponseEntity<Payment> getPayment(@PathVariable("pid") String pid) {
        return new ResponseEntity<>(paymentService.findByPid(pid), HttpStatus.OK);
    }

    // GET payment entries by the user id.
    @RequestMapping(value = "/uid/{uid}", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> getAllPaymentEntriesByUser(@PathVariable("uid") String uid) {
        return new ResponseEntity<>(paymentService.findByUid(uid), HttpStatus.OK);
    }

    // GET total price of food ordering.
    @RequestMapping(value = "/total", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getTotalAmountToPay(@RequestHeader("Authentication") long authKey, @RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        double total = 0;

        // go through each flower id and it's count.
        for (String tid: payload.keySet()) {
            int itemCount = Integer.parseInt(payload.get(tid).toString());
            double itemPrice = flowerService.getPriceOf(tid);
            double subtotal = itemPrice * itemCount;

            total += subtotal;
        }

        response.put("success", "true");
        response.put("amount", total);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET loyalty points count of a given user.
    @RequestMapping(value = "/{uid}/loyalty", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getTotalLoyaltyPoints(@RequestHeader("Authentication") String authKey, @PathVariable("uid") String uid) {
        Map<String, String> response = new HashMap<>();

        if (sessionService.authenticate(authKey)) {
            List<Payment> paymentsByUser = paymentService.findByUid(uid);
            double tot = 0;

            for(Payment payment: paymentsByUser) { tot += payment.getLoyaltyPoints(); }

            response.put("success", "true");
            response.put("loyalty", Double.toString(tot));
        }
        else {
            response.put("success", "false");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ADD new entry for payment.
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> addPayment(@RequestHeader("Authentication") String authKey, @RequestBody Payment payment) {
        Map<String, String> response = new HashMap<>();

        if (sessionService.authenticate(authKey)) {
            payment.setPid((payment.getUid() + payment.getPaymentDate().toString() + new Date().toString()).toString());

            // instead the fId and count of each is sent.
            double tot = 0;
            Map<String, Integer> items = payment.getItemsAndCounts();
            // calculate
            for (String tId: items.keySet()) { tot += (flowerService.getPriceOf(tId) * items.get(tId)); }
            payment.setAmount(tot);

            // give loyalty points for the amount.
            payment.setLoyaltyPoints(tot * (0.01/100));

            // for credit cards we only keep the last 4 digits of the card number for,
            // security concerns.
            if (payment.getPaymentType().equals("card")) {
                Map<String, String> paymentDetails = payment.getPaymentDetails();
                String cardNumber = paymentDetails.get("number");
                String censoredCardNumber = "xxxx-xxxx-xxxx-" + cardNumber.substring(cardNumber.length()-4, cardNumber.length());
                paymentDetails.put("number", censoredCardNumber);
            }
            
            // but we'll direct if the payment is successful.
            paymentService.savePayment(payment);
            response.put("success", "true");
            response.put("redirect", "home.html");
            response.put("pid", payment.getPid());
        }
        else {
            response.put("success", "false");
            response.put("redirect", "buy.html");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
