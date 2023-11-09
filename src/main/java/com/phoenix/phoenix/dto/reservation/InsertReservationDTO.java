package com.phoenix.phoenix.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertReservationDTO {
    private String reservationCode;
    private String reservationMethod;
    private String roomNumber;
    private String username;
    private LocalDateTime bookDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Double cost;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String remark;
}



