package com.phoenix.phoenix.dto.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertRoomDTO {
    @NotBlank(message = "Room Number cannot be empty ")
    @Size(max = 10,message = "Room Number cannot be more than 10 Character")
    private String number;

    @NotNull(message = "Floor cannot be empty")
    private Long floor;

    @NotBlank(message = "Room type cannot be empty")
    @Size(max = 3,message = "Room type cannot be more than 3 Characters")
    private String roomType;

    @NotNull(message = "Guest Limit cannot be empty")
    private Long guestLimit;

    private String description;

    @NotNull(message = "Cost cannot be empty")
    private Double cost;

}
