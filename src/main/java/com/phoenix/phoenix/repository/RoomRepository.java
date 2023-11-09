package com.phoenix.phoenix.repository;

import com.phoenix.phoenix.dto.myRoom.MyRoomDTO;
import com.phoenix.phoenix.dto.room.RoomRowDTO;
import com.phoenix.phoenix.dto.utility.DropdownDTO;
import com.phoenix.phoenix.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,String> {

    @Query("""
            SELECT new com.phoenix.phoenix.dto.room.RoomRowDTO(
            ro.number, ro.floor, ro.roomType, ro.guestLimit, ro.cost, ro.status
            )
            FROM Room AS ro
            WHERE ro.number LIKE %:roomNumber%
            AND (:roomType IS NULL OR :roomType = ro.roomType)
            AND (:status IS NULL OR :status = ro.status)
            
            """)
    public Page<RoomRowDTO> getRow(@Param("roomNumber") String number,
                                   @Param("roomType") String roomType,
                                   @Param("status") Boolean status,
                                   Pageable pageable);

    @Query("""
            SELECT new com.phoenix.phoenix.dto.myRoom.MyRoomDTO(
            ro.number,
            ro.floor,
            ro.roomType,
            ro.guestLimit,
            ro.cost,
            res.checkIn,
            res.checkOut,
            DATEDIFF(DAY,res.checkIn,res.checkOut)*ro.cost,
            res.paymentDate,
            res.paymentMethod,
            res.remark
            )
            FROM Room AS ro
                JOIN Reservation AS res ON ro.number = res.roomNumber
                JOIN Guest AS gus ON res.guestUsername = gus.username
            WHERE res.guestUsername = :guestUsername
            """)
    public List<MyRoomDTO> getRoomDetail(@Param("guestUsername") String guestUsername);

    @Query("""
            SELECT new com.phoenix.phoenix.dto.utility.DropdownDTO(
            ro.roomType,
            ro.roomType
            )
            FROM Room AS ro
            """)
    public List<DropdownDTO> getRoomTypeDropdown ();
}
