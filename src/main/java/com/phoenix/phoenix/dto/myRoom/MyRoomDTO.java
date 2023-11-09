package com.phoenix.phoenix.dto.myRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyRoomDTO {
    private String roomNumber;
    private Long floor;
    private String type;
    private Long guestLimit;
    private Double costPerNight;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Double totalCost;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String remark;
}
