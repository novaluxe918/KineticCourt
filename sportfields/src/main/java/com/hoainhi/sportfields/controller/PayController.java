package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.service.impl.BookingServiceimpl;
import com.hoainhi.sportfields.service.impl.PaypalServiceimpl;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paypal")
public class PayController {

    public static final String SUCCESS_URL = "http://localhost:8080/paypal/success";
    public static final String CANCEL_URL = "http://localhost:8080/paypal/cancel";

    @Autowired
    private PaypalServiceimpl paypalServiceimpl;

    @Autowired
    private BookingServiceimpl bookingServiceimpl;

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
                             Model model) {
        try {
            Payment payment = paypalServiceimpl.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                model.addAttribute("message", "Thanh toán thành công!");
                return "client/pay/success";
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
