package com.phoenix.phoenix.service;

import com.phoenix.phoenix.dto.myRoom.MyRoomDTO;
import com.phoenix.phoenix.dto.room.RoomDetailDTO;
import com.phoenix.phoenix.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyRoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<MyRoomDTO> getMyRoom(String guestUsername){
        return roomRepository.getRoomDetail(guestUsername);
    }
}
