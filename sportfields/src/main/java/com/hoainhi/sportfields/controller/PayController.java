package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.BookingServiceDTO;
import com.hoainhi.sportfields.entity.*;
import com.hoainhi.sportfields.enums.BookingStatus;
import com.hoainhi.sportfields.service.BookingServiceItemService;
import com.hoainhi.sportfields.service.impl.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/paypal")
public class PayController {

    public static final String SUCCESS_URL = "http://localhost:8080/paypal/success";
    public static final String CANCEL_URL = "http://localhost:8080/paypal/cancel";

    @Autowired
    private PaypalServiceimpl paypalServiceimpl;

    @Autowired
    private BookingServiceimpl bookingServiceimpl;

    @Autowired
    private ScheduleDetailSerivceimp scheduleDetailSerivceimp;

    @Autowired
    private ServiceImpl service;

    @Autowired
    private BookingServiceItemService bookingServiceItemService;

    @Autowired
    private BookingDetailServiceImpl bookingDetailService;
    // Xử lý gửi request thanh toán sang PayPal
    @GetMapping ("/pay")
    public String makePayment(HttpSession session) {
        try {
            Double courtTotal = (Double) session.getAttribute("courtTotal");
            Payment payment = paypalServiceimpl.createPayment(
                  courtTotal,
                    "USD",
                    "paypal",
                    "sale",
                    "Payment description",
                    CANCEL_URL,
                    SUCCESS_URL
            );

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/paypal";
    }

    // Xử lý khi thanh toán thành công
    @GetMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId,
                             Model model, HttpSession session) {
        try {
            Payment payment = paypalServiceimpl.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                User user =
                        (User) session.getAttribute("bookingUser");

                List<Long> selectedSlots =
                        (List<Long>) session.getAttribute("selectedSlots");

                List<BookingServiceDTO> selectedServices =
                        (List<BookingServiceDTO>)
                                session.getAttribute("selectedServices");

                Double courtTotal =
                        (Double) session.getAttribute("courtTotal");

                LocalDate bookingDate =
                        (LocalDate) session.getAttribute("bookingDate");

                Booking booking = new Booking();
                booking.setUser(user);
                booking.setTotal(courtTotal);
                booking.setBooking_date(bookingDate);
                booking.setStatus(BookingStatus.PENDING);
                booking = bookingServiceimpl.saveBooking(booking);


                List<ScheduleDetails> details = scheduleDetailSerivceimp.getByIds(selectedSlots);

                for(ScheduleDetails scheduleDetails : details){
                    BookingDetails bookingDetails = new BookingDetails();
                    bookingDetails.setBooking(booking);
                    bookingDetails.setScheduleDetails(scheduleDetails);
                   bookingDetailService.save(bookingDetails);
                }
                if (selectedServices != null) {

                    for (BookingServiceDTO serviceDTO : selectedServices) {

                        Services services =
                                service.findById(
                                        serviceDTO.getServiceId()
                                );

                        BookingService bookingService =
                                new BookingService();

                        bookingService.setBooking(booking);

                        bookingService.setService(services);

                        bookingService.setQuantity(
                                serviceDTO.getQuantity()
                        );

                        bookingService.setPrice(
                                serviceDTO.getPrice()
                        );

                       bookingServiceItemService.save(bookingService);
                    }
                }
                model.addAttribute("message", "Thanh toán thành công!");
                return "redirect:/booking/history";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/paypal";
    }

    // Xử lý khi người dùng huỷ thanh toán
    @GetMapping("/cancel")
    public String cancelPay(Model model) {
        model.addAttribute("message", "Giao dịch đã bị huỷ.");
        return "client/pay/cancel";
    }
}
