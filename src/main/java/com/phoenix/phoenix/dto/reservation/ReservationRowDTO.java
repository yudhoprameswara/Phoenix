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
public class ReservationRowDTO {
    private String code;
    private String roomNumber;
    private String username;
    private LocalDateTime bookDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private LocalDateTime paymentDate;
}
