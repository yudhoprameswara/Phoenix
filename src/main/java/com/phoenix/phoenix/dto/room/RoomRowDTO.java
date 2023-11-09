package com.phoenix.phoenix.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRowDTO {
    private String number;
    private Long floor;
    private String type;
    private Long guestLimit;
    private Double costPerDay;
    private Boolean status;


}
