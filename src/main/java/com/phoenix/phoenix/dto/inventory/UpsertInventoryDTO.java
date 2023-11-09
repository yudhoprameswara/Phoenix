package com.phoenix.phoenix.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertInventoryDTO {
    private String name;
    private Long stock;
    private String description;
}
