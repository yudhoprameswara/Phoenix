package com.phoenix.phoenix.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRowDTO {
    public String inventoryName;
    public Long stock;
    public String description;
}
