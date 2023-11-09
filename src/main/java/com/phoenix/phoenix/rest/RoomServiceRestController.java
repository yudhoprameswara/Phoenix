package com.phoenix.phoenix.rest;

import com.phoenix.phoenix.dto.roomService.UpsertRoomServiceDTO;
import com.phoenix.phoenix.service.RoomServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roomService")
public class RoomServiceRestController {

    @Autowired
    private RoomServicesService service;

    @GetMapping("/index")
    public ResponseEntity<Object> getRowIndex(@RequestParam(defaultValue = "") String employeeNumber,
                                              @RequestParam(defaultValue = "") String name,
                                              @RequestParam(defaultValue = "1") Integer page){
        var response = service.getRows(employeeNumber,name,page);
        return ResponseEntity.status(201).body(response);
    }
    @GetMapping("/{employeeNumber}")
    public ResponseEntity<Object> getRoomServiceById(@PathVariable String employeeNumber){
        var response = service.getRoomServiceById(employeeNumber);
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/upsert")
    public ResponseEntity<Object> upsertRoomService(@RequestBody UpsertRoomServiceDTO dto){
        service.save(dto);
        return ResponseEntity.status(201).body("Upsert successful");
    }
}
