package com.phoenix.phoenix.service;

import com.phoenix.phoenix.dto.inventory.InventoryRowDTO;
import com.phoenix.phoenix.dto.inventory.UpsertInventoryDTO;
import com.phoenix.phoenix.entity.Inventory;
import com.phoenix.phoenix.entity.RoomInventory;
import com.phoenix.phoenix.repository.InventoryRepository;
import com.phoenix.phoenix.repository.RoomInventoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class InventoryService {

    @Autowired
    public InventoryRepository inventoryRepository;

    @Autowired
    public RoomInventoriesRepository roomInventoriesRepository;

    public Page<InventoryRowDTO> getRows(Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("name"));
        var rows = inventoryRepository.getRows(pageable);
        return rows;
    }

    public Inventory save(UpsertInventoryDTO dto){
        Inventory entity = new Inventory();
        entity.setName(dto.getName());
        entity.setStock(dto.getStock());
        entity.setDescription(dto.getDescription());
        inventoryRepository.save(entity);
        return entity;
    }

    public void delete (String inventoryName){
        inventoryRepository.deleteById(inventoryName);
    }

    public Long dependencies (String inventoryName){
    return roomInventoriesRepository.dependenciesByInventory(inventoryName);
    }
}
