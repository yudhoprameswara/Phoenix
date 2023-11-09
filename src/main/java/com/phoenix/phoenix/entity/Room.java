package com.phoenix.phoenix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @Column(name = "Number")
    private String number;

    @Column(name = "Floor")
    private Long floor;

    @Column(name = "RoomType")
    private String roomType;

    @Column(name = "GuestLimit")
    private Long guestLimit;

    @Column(name = "Description")
    private String description;

    @Column(name = "Cost")
    private Double cost;

    @Column(name = "Status")
    private Boolean status;
}
