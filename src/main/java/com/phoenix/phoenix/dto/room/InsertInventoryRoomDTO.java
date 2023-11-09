package com.phoenix.phoenix.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertInventoryRoomDTO {
    private String roomNumber;
    private String inventoryName;
    private Long quantity;

}
