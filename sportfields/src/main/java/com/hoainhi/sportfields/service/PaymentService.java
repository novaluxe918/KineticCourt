package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.entity.Payments;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    Payments savePayment(Payments payment);
    Payments findById(Long id);
}
