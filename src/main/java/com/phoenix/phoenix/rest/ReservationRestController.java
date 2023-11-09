package com.phoenix.phoenix.rest;

import com.phoenix.phoenix.dto.reservation.InsertReservationDTO;
import com.phoenix.phoenix.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {

    @Autowired
    public ReservationService service;

    @GetMapping("/index")
    public ResponseEntity<Object> getRows(@RequestParam(defaultValue = "") String roomNumber,
                                          @RequestParam(defaultValue = "") String username,
                                          @RequestParam(required = false) LocalDateTime bookBefore,
                                          @RequestParam(defaultValue = "1") Integer page){
        var response = service.getRows(roomNumber,username,bookBefore,page);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<Object> getTotalPrice(@RequestParam(required = false) Long month,
                                                @RequestParam(required = false) Long year){
        var response = service.getTotalIncome(month, year);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/details/{code}")
    public ResponseEntity<Object> getReservationDetails(@PathVariable String code){
        var response = service.getReservationDetail(code);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/makeReservation")
    public  ResponseEntity<Object> makeReservation(@RequestBody InsertReservationDTO dto){
        service.save(dto);
        return ResponseEntity.status(201).body("Reservation successful");
    }
}
