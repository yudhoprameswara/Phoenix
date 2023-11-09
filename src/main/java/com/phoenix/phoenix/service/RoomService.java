package com.phoenix.phoenix.service;

import com.phoenix.phoenix.dto.room.*;
import com.phoenix.phoenix.dto.utility.DropdownDTO;
import com.phoenix.phoenix.entity.Room;
import com.phoenix.phoenix.entity.RoomInventory;
import com.phoenix.phoenix.repository.InventoryRepository;
import com.phoenix.phoenix.repository.RoomInventoriesRepository;
import com.phoenix.phoenix.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomInventoriesRepository roomInventoriesRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public Page<RoomRowDTO> getRow(String roomNumber,String roomType,Boolean status,Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("number"));
        var rows = roomRepository.getRow(roomNumber,roomType,status,pageable);
        return rows;
    }

    public Room save(UpsertRoomDTO dto){
        var entity = new Room();
        entity.setNumber(dto.getNumber());
        entity.setFloor(dto.getFloor());
        entity.setRoomType(dto.getRoomType());
        entity.setGuestLimit(dto.getGuestLimit());
        entity.setDescription(dto.getDescription());
        entity.setCost(dto.getCost());
        entity.setStatus(true);
        roomRepository.save(entity);
        return entity;
    }

    public Object saveRoomInventory(InsertInventoryRoomDTO dto){
        var entity = new RoomInventory();
        var inventory = inventoryRepository.findById(dto.getInventoryName()).get();
        var stockVsQuantity = inventory.getStock() - dto.getQuantity();

        if (stockVsQuantity >= 0){
            entity.setRoomNumber(dto.getRoomNumber());
            entity.setInventoryName(dto.getInventoryName());
            entity.setQuantity(dto.getQuantity());
            inventory.setStock(stockVsQuantity);
            inventoryRepository.save(inventory);
            roomInventoriesRepository.save(entity);
            return entity;
        }
       return "Stock is Insufficient";
    }

    public Page<RoomInventoriesRowDTO> getDetailRow(String roomNumber,Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
       return roomInventoriesRepository.getRows(roomNumber,pageable);
    }

    public RoomDetailDTO getDetail(String roomId){
       var entity = roomRepository.findById(roomId).get();
       RoomDetailDTO dto = new RoomDetailDTO();
       dto.setRoomNumber(entity.getNumber());
       dto.setFloor(entity.getFloor());
       dto.setRoomType(entity.getRoomType());
       dto.setGuestLimit(entity.getGuestLimit());
       return dto;
    }

    public List<DropdownDTO> getInventoriesDropdown(){
        return inventoryRepository.getInventoryDropdown();
    }

    public List<DropdownDTO> getRoomTypeDropdown(){return roomRepository.getRoomTypeDropdown();}

    public void deleteRoomInventories(Long roomInventory){
      var roomInventories = roomInventoriesRepository.findById(roomInventory).get();
      var quantity = roomInventories.getQuantity();
      var inventoryName = roomInventories.getInventoryName();
      var inventory = inventoryRepository.findById(inventoryName).get();
      inventory.setStock(inventory.getStock() + quantity);
      inventoryRepository.save(inventory);
      roomInventoriesRepository.deleteById(roomInventory);

    }

    public Room getRoomByNumber (String roomNumber){
        return roomRepository.findById(roomNumber).get();
    }
}
