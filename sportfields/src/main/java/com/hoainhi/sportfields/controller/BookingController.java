package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.*;
import com.hoainhi.sportfields.entity.*;
import com.hoainhi.sportfields.enums.ScheduleStatus;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private CourtServiceImpl courtService;

    @Autowired
    private ScheduleDetailSerivceimp scheduleDetailSerivceimp;

    @Autowired
    private BookingServiceimpl bookingServiceimpl;
    @Autowired
    private ServiceImpl service;

    @Autowired
    private FacilitySeviceimpl facilitySeviceimpl;

    @GetMapping("/book")
    public String bookingClient(@RequestParam Long facilityId, Model model,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date, HttpSession session){


        User user = (User) session.getAttribute("loginUser");

        if(user == null){
            return "redirect:/facility/view/" + facilityId + "?error=loginRequired";
        }
        LocalDate today = LocalDate.now();
        if(date == null){
            date = today;
        }  // thoi gian hien tai

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        List<String> timeSlots = new ArrayList<>(); // dto time , status
        LocalTime time = LocalTime.of(5, 0);
        LocalTime end = LocalTime.of(22, 0);
        while (!time.isAfter(end)) {
            timeSlots.add(time.format(formatter));
            time = time.plusMinutes(30);
        }
        List<Court> courts =
                courtService.getCourtByFacility(facilityId);

        List<ShowDTO> showDTOS =
                new ArrayList<>();

        for (Court court : courts) {

            // Lấy schedule của sân trong ngày được chọn
            List<ScheduleDetails> scheduleDetails =
                    scheduleDetailSerivceimp.getScheduleDetails(
                            court.getId(),
                            date
                    );

            List<BookingBlockDTO> bookingBlockDTOS =
                    new ArrayList<>();

            for (ScheduleDetails details : scheduleDetails) {

                LocalTime start =
                        details.getTime_start();

                LocalTime endTime =
                        details.getTime_end();



                // 1. TÍNH VỊ TRÍ BẮT ĐẦU

                long minuteFromStart =
                        Duration.between(
                                LocalTime.of(5, 0),
                                start
                        ).toMinutes();

                int startSlot =
                        (int) (minuteFromStart / 30);



                // 2. TÍNH SỐ SLOT

                long durationMinutes =
                        Duration.between(start, endTime).toMinutes();

                int slotCount =
                        (int) (durationMinutes / 30);




                BookingBlockDTO blockDTO =
                        new BookingBlockDTO();

                blockDTO.setStartTime(start);
                blockDTO.setEndTime(endTime);
                blockDTO.setId(details.getId());
                blockDTO.setStartColumn(startSlot);
                blockDTO.setSlotCount(slotCount);
                blockDTO.setPrice(details.getPrice());
                bookingBlockDTOS.add(blockDTO);
            }


            ShowDTO showDTO =
                    new ShowDTO();

            showDTO.setCourt(court);
            showDTO.setBookingBlocks(
                    bookingBlockDTOS
            );

            showDTOS.add(showDTO);
        }
        model.addAttribute("date", date);
        model.addAttribute("today", today);
        model.addAttribute("facilityId", facilityId);
        model.addAttribute("showDTOS", showDTOS);
        model.addAttribute("timeSlot", timeSlots);

        return "client/booking/Booking";
    }

    @GetMapping("/booking_detail")
    public String bookingDetail(@RequestParam Long facilityId,@RequestParam List<Long> selectedSlots, @RequestParam LocalDate date ,Model model, Pageable pageable, HttpSession session) {

        Page<Services> servicesPage = service.findByFacility_Id(facilityId, pageable);
        List<ScheduleDetails> details = scheduleDetailSerivceimp.getByIds(selectedSlots);
        double courtTotal = details.stream().mapToDouble(ScheduleDetails::getPrice).sum();
        User user = (User) session.getAttribute("loginUser");

        session.setAttribute("bookingUser", user);
        session.setAttribute("selectedSlots", selectedSlots);
        session.setAttribute("courtTotal", courtTotal);
        session.setAttribute("facilityId", facilityId);
        session.setAttribute("bookingDate", date);

        model.addAttribute("page", servicesPage);
        model.addAttribute("facilityId", facilityId);
        model.addAttribute("details", details);
        model.addAttribute("selectSlots", selectedSlots);
        model.addAttribute("courtTotal", courtTotal);
        model.addAttribute("bookingDate", date);
        return "client/booking/BookingDetail";
    }

    @GetMapping("/history")
    public String bookingHistory(HttpSession session, Model model){
        User user = (User) session.getAttribute("loginUser");
        List<Booking> bookings = bookingServiceimpl.getBookingHistory(user.getId());
         // tao list enum =>
        model.addAttribute("bookings", bookings);
        return "client/booking/BookingHistory";
    }

// controller
    @PostMapping("/checkout")
    public String checkout( @RequestParam(required = false) List<Long> serviceIds,
                            @RequestParam(required = false) List<Integer> quantities,
                            HttpSession session){

        List<BookingServiceDTO> bookingServiceDTOS = new ArrayList<>();
        if(serviceIds != null && quantities != null){
            for (int i = 0; i < serviceIds.size(); i++) {

                Integer quantity = quantities.get(i);

                if (quantity != null && quantity > 0) {

                    Services services =
                           service.findById(serviceIds.get(i));

                    BookingServiceDTO dto =
                            new BookingServiceDTO();

                    dto.setServiceId(services.getId());
                    dto.setQuantity(quantity);
                    dto.setPrice(services.getPrice());

                    bookingServiceDTOS.add(dto);
                }
            }
        }
        session.setAttribute("selectedServices", bookingServiceDTOS);
        return "redirect:/paypal/pay";
    }

    @GetMapping("/booking_own")
    public String bookingOwner(HttpServletRequest request, Model model){
        model.addAttribute("currentUrl", request.getRequestURI());
        return "owner/booking/Booking";
    }

}
