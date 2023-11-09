package com.phoenix.phoenix.rest;

import com.phoenix.phoenix.dto.administrator.UpsertAdministratorDTO;
import com.phoenix.phoenix.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/administrator")
public class AdministratorRestController {

    @Autowired
    private AdministratorService service;

    @GetMapping("/index")
    public ResponseEntity<Object> getRows(@RequestParam(defaultValue = "1") Integer page) {
        var dto = service.getRow(page);
        return ResponseEntity.status(201).body(dto);
    }

    @PostMapping("/upsert")
    public ResponseEntity<Object> upsert(@RequestBody UpsertAdministratorDTO dto){
        service.save(dto);
        return  ResponseEntity.status(201).body("Update Successful");
    }

}