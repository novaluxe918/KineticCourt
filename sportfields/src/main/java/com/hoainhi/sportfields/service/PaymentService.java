package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.PaymentHistoryDTO;
import com.hoainhi.sportfields.entity.Payments;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    Payments savePayment(Payments payment);
    Payments findById(Long id);
    List<PaymentHistoryDTO> getPaymentHistoryByUser(Long id );

}
