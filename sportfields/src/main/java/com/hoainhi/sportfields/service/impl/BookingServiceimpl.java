package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.BookingBlockDTO;
import com.hoainhi.sportfields.dto.BookingDetailDTO;
import com.hoainhi.sportfields.dto.BookingServiceDTO;
import com.hoainhi.sportfields.dto.ShowDTO;
import com.hoainhi.sportfields.entity.*;
import com.hoainhi.sportfields.enums.BookingStatus;
import com.hoainhi.sportfields.repository.BookingRepository;
import com.hoainhi.sportfields.service.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceimpl implements BookingServices {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourtServiceImpl courtService;

    @Autowired
    private ScheduleDetailSerivceimp  scheduleDetailSerivceimp;
    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingHistory(Long userId) {
        List<Booking> bookings = bookingRepository.findBookingHistory(userId);
        LocalDate today = LocalDate.now();
        for(Booking booking : bookings){
            if(booking.getStatus() == BookingStatus.APPROVE && booking.getBooking_date().isBefore(today)){
                booking.setStatus(BookingStatus.COMPLETED);
                bookingRepository.save(booking);
            }
        }
        return bookings;
    }

    @Override
    public BookingDetailDTO getBookingDetail(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        BookingDetailDTO dto = new BookingDetailDTO();
        dto.setId(booking.getId());

        dto.setStatus(booking.getStatus());

        dto.setBooking_date(
                booking.getBooking_date()
        );

        dto.setTotal(
                booking.getTotal()
        );


        // =========================
        // Thông tin sân
        // =========================

        if (booking.getBookingDetail() != null
                && !booking.getBookingDetail().isEmpty()) {

            BookingDetails detail =
                    booking.getBookingDetail().get(0);

            ScheduleDetails scheduleDetail =
                    detail.getScheduleDetails();

            if (scheduleDetail != null) {

                dto.setTime_start(
                        scheduleDetail.getTime_start()
                );

                dto.setTime_end(
                        scheduleDetail.getTime_end()
                );


                Schedule schedule =
                        scheduleDetail.getSchedule();

                if (schedule != null) {

                    Court court =
                            schedule.getCourt();

                    if (court != null) {

                        dto.setName_court(
                                court.getName_court()
                        );


                        Facility facility =
                                court.getFacility();

                        if (facility != null) {

                            dto.setName_facility(
                                    facility.getName_facility()
                            );

                            dto.setAddress(
                                    facility.getAddress()
                            );
                        }
                    }
                }
            }
        }


        // =========================
        // Dịch vụ
        // =========================

        List<BookingServiceDTO> services =
                new ArrayList<>();

        if (booking.getBookingService() != null) {

            for (BookingService item :
                    booking.getBookingService()) {

                BookingServiceDTO serviceDTO =
                        new BookingServiceDTO();

                if (item.getService() != null) {

                    serviceDTO.setTitle(
                            item.getService().getTitle()
                    );
                }

                serviceDTO.setQuantity(
                        item.getQuantity()
                );

                serviceDTO.setPrice(
                        item.getPrice()
                );

                services.add(serviceDTO);
            }
        }

        dto.setServices(services);


        return dto;
    }

    @Override
    public List<ShowDTO> getShowBooking(Long facilityId, LocalDate date) {
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

                boolean booked = bookingRepository.existsBooking(date, details.getId());

                blockDTO.setStartTime(start);
                blockDTO.setEndTime(endTime);
                blockDTO.setId(details.getId());
                blockDTO.setStartColumn(startSlot);
                blockDTO.setSlotCount(slotCount);
                blockDTO.setPrice(details.getPrice());
                blockDTO.setBooked(booked);
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
        return showDTOS;
    }


}
