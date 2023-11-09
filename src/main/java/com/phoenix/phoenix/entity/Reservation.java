package com.phoenix.phoenix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @Column(name = "Code")
    private String code;

    @Column(name = "ReservationMethod")
    private String reservationMethod;

    @Column(name = "RoomNumber")
    private String roomNumber;

    @Column(name = "GuestUsername")
    private String guestUsername;

    @Column(name = "BookDate")
    private LocalDateTime bookDate;

    @Column(name = "CheckIn")
    private LocalDateTime checkIn;

    @Column(name = "CheckOut")
    private LocalDateTime checkOut;

    @Column(name = "Cost")
    private Double cost;

    @Column(name = "PaymentDate")
    private LocalDateTime paymentDate;

    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @Column(name = "Remark")
    private String remark;
}
