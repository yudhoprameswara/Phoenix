package com.phoenix.phoenix.service;

import com.phoenix.phoenix.dto.roomService.RoomServiceRowDTO;
import com.phoenix.phoenix.dto.roomService.UpsertRoomServiceDTO;
import com.phoenix.phoenix.entity.RoomService;
import com.phoenix.phoenix.repository.RoomServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RoomServicesService {

    @Autowired
    private RoomServiceRepository roomServiceRepository;

    public Page<RoomServiceRowDTO> getRows(String employeeNumber, String name, Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("employeeNumber"));
        var rows = roomServiceRepository.getRow(employeeNumber,name,pageable);
        return rows;
    }

    public Optional<RoomService> getRoomServiceById(String employeeNumber){
        var entity = roomServiceRepository.findById(employeeNumber);
        return entity;
    }

    public void save(UpsertRoomServiceDTO dto){
        var entity = new RoomService();
        entity.setEmployeeNumber(dto.getEmployeeNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setOutsourcingCompany(dto.getOutsourcingCompany());
        entity.setMondayRosterStart(dto.getMondayRosterStart());
        entity.setMondayRosterFinish(dto.getMondayRosterFinish());
        entity.setTuesdayRosterStart(dto.getTuesdayRosterStart());
        entity.setTuesdayRosterFinish(dto.getTuesdayRosterFinish());
        entity.setWednesdayRosterStart(dto.getWednesdayRosterStart());
        entity.setWednesdayRosterFinish(dto.getWednesdayRosterFinish());
        entity.setThursdayRosterStart(dto.getThursdayRosterStart());
        entity.setThursdayRosterFinish(dto.getThursdayRosterFinish());
        entity.setFridayRosterStart(dto.getFridayRosterStart());
        entity.setFridayRosterFinish(dto.getFridayRosterFinish());
        entity.setSaturdayRosterStart(dto.getSaturdayRosterStart());
        entity.setSaturdayRosterFinish(dto.getSaturdayRosterFinish());
        entity.setSundayRosterStart(dto.getSundayRosterStart());
        entity.setSundayRosterFinish(dto.getSundayRosterFinishEmployeeNumber());
        roomServiceRepository.save(entity);
    }
}
