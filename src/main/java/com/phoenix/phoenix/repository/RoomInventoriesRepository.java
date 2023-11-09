package com.phoenix.phoenix.repository;

import com.phoenix.phoenix.dto.room.RoomInventoriesRowDTO;
import com.phoenix.phoenix.entity.RoomInventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomInventoriesRepository extends JpaRepository<RoomInventory,Long> {

    @Query("""
            SELECT new com.phoenix.phoenix.dto.room.RoomInventoriesRowDTO(
                ris.id,
                ris.inventoryName,
                inv.stock,
                ris.quantity
            )
            FROM 
                RoomInventory AS ris 
                JOIN Inventory AS inv ON ris.inventoryName = inv.name
            WHERE ris.roomNumber = :roomId    
            """)
    public Page<RoomInventoriesRowDTO> getRows(@Param("roomId")String roomId, Pageable pageable);

    @Query("""
            SELECT COUNT(ris.inventoryName)
            FROM  RoomInventory AS ris
            WHERE ris.inventoryName = :inventoryName
            """)
    public Long dependenciesByInventory (@Param("inventoryName") String inventoryName);
}
