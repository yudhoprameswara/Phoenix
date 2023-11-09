package com.phoenix.phoenix.repository;

import com.phoenix.phoenix.dto.roomService.RoomServiceRowDTO;
import com.phoenix.phoenix.entity.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomServiceRepository extends JpaRepository<RoomService,String> {

    @Query("""
            SELECT new  com.phoenix.phoenix.dto.roomService.RoomServiceRowDTO(
                ros.employeeNumber,
                CONCAT(ros.firstName,' ',ros.middleName,' ',ros.lastName),
                ros.outsourcingCompany
            )
            FROM RoomService AS ros
            WHERE CONCAT(ros.firstName,' ',ros.lastName) LIKE %:name%
            AND ros.employeeNumber LIKE %:employeeNumber%
            """)
    public Page<RoomServiceRowDTO> getRow(@Param("employeeNumber") String employeeNumber,
                                          @Param("name") String name,
                                          Pageable pageable);
}
