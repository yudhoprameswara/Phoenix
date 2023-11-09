package com.phoenix.phoenix.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInventoriesRowDTO {
    private Long id;
    private String inventoryName;
    private Long stock;
    private Long quantity;
}
