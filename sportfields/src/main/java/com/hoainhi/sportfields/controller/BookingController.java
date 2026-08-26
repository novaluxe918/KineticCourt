package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.*;
import com.hoainhi.sportfields.entity.*;
import com.hoainhi.sportfields.enums.ScheduleStatus;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.service.impl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String bookingDetail(@RequestParam Long facilityId,@RequestParam List<Long> selectedSlots ,Model model, Pageable pageable) {

        Page<Services> servicesPage = service.findByFacility_Id(facilityId, pageable);
        List<ScheduleDetails> details = scheduleDetailSerivceimp.getByIds(selectedSlots);
        double courtTotal = details.stream().mapToDouble(ScheduleDetails::getPrice).sum();
        model.addAttribute("page", servicesPage);
        model.addAttribute("facilityId", facilityId);
        model.addAttribute("details", details);
        model.addAttribute("selectSlots", selectedSlots);
        model.addAttribute("courtTotal", courtTotal);
        return "client/booking/BookingDetail";
    }

//    @GetMapping("/confirm_bk")
//    public String confirmBooking(HttpSession session, @ModelAttribute BookingDTO bookingDTO){
//        User user = (User) session.getAttribute("loginUser");
//        bookingServiceimpl.saveBooking(bookingDTO, user);
//        return "client/booking/ConfirmBooking";
//    }

}
