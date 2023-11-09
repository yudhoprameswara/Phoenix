package com.phoenix.phoenix.rest;

import com.phoenix.phoenix.dto.room.InsertInventoryRoomDTO;
import com.phoenix.phoenix.dto.room.UpsertRoomDTO;
import com.phoenix.phoenix.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/room")
public class RoomRestController {

    @Autowired
    private RoomService service;

    @GetMapping("/index")
    public ResponseEntity<Object> getRowIndex( @RequestParam(defaultValue = "") String roomNumber,
                                               @RequestParam(required = false) String roomType,
                                               @RequestParam(required = false) Boolean status,
                                               @RequestParam(defaultValue = "1") Integer page){
        var response = service.getRow(roomNumber,roomType,status,page);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<Object> getRoomByNumber(@PathVariable String roomNumber){
        var response = service.getRoomByNumber(roomNumber);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/upsert")
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertRoomDTO dto){
        var response = service.save(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/detailRow/{roomId}")
    public ResponseEntity<Object> getDetailsRow(@PathVariable String roomId,
                                             @RequestParam(defaultValue = "1") Integer page){
        var response = service.getDetailRow(roomId,page);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/detail/{roomId}")
    public ResponseEntity<Object> getDetails(@PathVariable String roomId
                                               ){
        var response = service.getDetail(roomId);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/inventory/dropdown")
    public ResponseEntity<Object> getDropdown(){
        var response = service.getInventoriesDropdown();
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/roomType/dropdown")
    public ResponseEntity<Object> getRoomTypeDropdown(){
        var response = service.getRoomTypeDropdown();
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/inventory/insert")
    public ResponseEntity<Object> post(@Valid @RequestBody InsertInventoryRoomDTO dto){
        var response = service.saveRoomInventory(dto);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/delete/{roomInventoryId}")
    public ResponseEntity<Object> deleteDetails(@PathVariable Long roomInventoryId){
        service.deleteRoomInventories(roomInventoryId);
        return ResponseEntity.status(201).body("delete successful");
    }
}
