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
public class ReservationDetailDTO {
    private String reservationCode;
    private String reservationMethod;
    private String roomNumber;
    private Long roomFloor;
    private String roomType;
    private String username;
    private String fullName;
    private LocalDateTime bookDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Double cost;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String remark;
}
