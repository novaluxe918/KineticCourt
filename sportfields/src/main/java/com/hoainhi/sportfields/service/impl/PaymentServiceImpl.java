package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.PaymentHistoryDTO;
import com.hoainhi.sportfields.entity.BookingDetails;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Payments;
import com.hoainhi.sportfields.entity.ScheduleDetails;
import com.hoainhi.sportfields.repository.PaymentRepository;
import com.hoainhi.sportfields.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<PaymentHistoryDTO> getPaymentHistoryByUser(Long id) {
        List<Payments> payments = paymentRepository.findByBooking_User_Id(id);
        List<PaymentHistoryDTO> historyDTOS = new ArrayList<>();
        for(Payments payment : payments){
            PaymentHistoryDTO paymentHistoryDTO = new PaymentHistoryDTO();
            paymentHistoryDTO.setPaymentId(payment.getId());
            paymentHistoryDTO.setTransaction_code(payment.getTransaction_code());
            paymentHistoryDTO.setBookingId(payment.getBooking().getId());
            paymentHistoryDTO.setPayment_date(payment.getPayment_date());
            paymentHistoryDTO.setAmount(payment.getAmount());
            paymentHistoryDTO.setPayment_method(payment.getPayment_method());
            paymentHistoryDTO.setStatus(payment.getStatus());

            if(payment.getBooking().getBookingDetail() != null  && !payment.getBooking().getBookingDetail().isEmpty()){
                BookingDetails detail =
                        payment.getBooking().getBookingDetail().get(0);

                ScheduleDetails scheduleDetails =
                        detail.getScheduleDetails();

                if (scheduleDetails != null
                        && scheduleDetails.getSchedule() != null
                        && scheduleDetails.getSchedule().getCourt() != null) {

                    Court court =
                            scheduleDetails.getSchedule().getCourt();

                    paymentHistoryDTO.setName_court(court.getName_court());

                    if (court.getFacility() != null) {
                        paymentHistoryDTO.setFacility_name(
                                court.getFacility().getName_facility()
                        );
                    }
                }
            }
            historyDTOS.add(paymentHistoryDTO);
        }
        return historyDTOS;
    }


}
