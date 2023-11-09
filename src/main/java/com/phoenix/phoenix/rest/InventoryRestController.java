package com.phoenix.phoenix.rest;

import com.phoenix.phoenix.dto.inventory.UpsertInventoryDTO;
import com.phoenix.phoenix.dto.room.UpsertRoomDTO;
import com.phoenix.phoenix.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/inventory")
public class InventoryRestController {

    @Autowired
    private InventoryService service;

    @GetMapping("/index")
    public ResponseEntity<Object> getRowIndex(@RequestParam(defaultValue = "1") Integer page){
        var response = service.getRows(page);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/upsert")
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertInventoryDTO dto){
        var response = service.save(dto);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/delete/{inventoryName}")
    public ResponseEntity<Object> delete(@PathVariable String inventoryName){
        var dependencies = service.dependencies(inventoryName);
        if (dependencies > 0){
            return ResponseEntity.status(409).body( "your item has " +dependencies + " dependencies to the room ");
        }
        service.delete(inventoryName);
        return ResponseEntity.status(201).body("delete successful");

    }
}
