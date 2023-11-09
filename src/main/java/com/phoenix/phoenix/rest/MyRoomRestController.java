package com.phoenix.phoenix.rest;

import com.phoenix.phoenix.service.MyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myRoom")
public class MyRoomRestController {

    @Autowired
    private MyRoomService service;

    @GetMapping("/{guestUsername}")
    public ResponseEntity<Object> getMyRoomDetail(@PathVariable String guestUsername){
        var dto = service.getMyRoom(guestUsername);
        return ResponseEntity.status(201).body(dto);
    }
}
