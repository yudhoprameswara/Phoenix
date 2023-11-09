package com.phoenix.phoenix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RoomInventories")
public class RoomInventory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "RoomNumber")
    private String roomNumber;

    @Column(name= "inventoryName")
    private String inventoryName;

    @Column(name = "Quantity")
    private Long quantity;
}
