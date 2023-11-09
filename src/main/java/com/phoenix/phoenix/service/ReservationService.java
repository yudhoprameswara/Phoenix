package com.phoenix.phoenix.service;

import com.phoenix.phoenix.dto.reservation.InsertReservationDTO;
import com.phoenix.phoenix.dto.reservation.ReservationDetailDTO;
import com.phoenix.phoenix.dto.reservation.ReservationRowDTO;
import com.phoenix.phoenix.entity.Reservation;
import com.phoenix.phoenix.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Page<ReservationRowDTO> getRows(String roomNumber, String username, LocalDateTime bookBefore, Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("bookDate"));
        var rows = reservationRepository.getRow(roomNumber,username,bookBefore,pageable);
        return rows;
    }

    public Double getTotalIncome(Long month,Long year){
        return reservationRepository.getTotalIncome(month, year);
    }

    public ReservationDetailDTO getReservationDetail(String code){
        return reservationRepository.getReservationDetail(code);
    }

    public void save (InsertReservationDTO dto){
        var reservation = new Reservation();
         reservation.setCode(dto.getReservationCode());
         reservation.setReservationMethod(dto.getReservationMethod());
         reservation.setRoomNumber(dto.getRoomNumber());
         reservation.setGuestUsername(dto.getUsername());
         reservation.setBookDate(dto.getBookDate());
         reservation.setCheckIn(dto.getCheckIn());
         reservation.setCheckOut(dto.getCheckOut());
         reservation.setCost(dto.getCost());
         reservation.setPaymentDate(dto.getPaymentDate());
         reservation.setPaymentMethod(dto.getPaymentMethod());
         reservation.setRemark(dto.getRemark());
         reservationRepository.save(reservation);
    }
}
