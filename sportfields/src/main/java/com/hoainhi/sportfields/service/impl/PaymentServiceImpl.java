package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.entity.Payments;
import com.hoainhi.sportfields.repository.PaymentRepository;
import com.hoainhi.sportfields.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payments savePayment(Payments payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payments findById(Long id) {
        return paymentRepository.findById(id).orElseThrow();
    }
}
