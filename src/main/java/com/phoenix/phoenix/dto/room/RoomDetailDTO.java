package com.phoenix.phoenix.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetailDTO {
    private String roomNumber;
    private Long floor;
    private String roomType;
    private Long guestLimit;
}
