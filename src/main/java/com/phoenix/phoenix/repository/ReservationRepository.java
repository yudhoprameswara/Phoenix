package com.phoenix.phoenix.repository;

import com.phoenix.phoenix.dto.reservation.ReservationDetailDTO;
import com.phoenix.phoenix.dto.reservation.ReservationRowDTO;
import com.phoenix.phoenix.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {

    @Query("""
            SELECT new com.phoenix.phoenix.dto.reservation.ReservationRowDTO(
                res.code,
                res.roomNumber,
                res.guestUsername,
                res.bookDate,
                res.checkIn,
                res.checkOut,
                res.paymentDate
            )
            FROM Reservation as res
            WHERE res.roomNumber LIKE %:roomNumber%
            AND res.guestUsername LIKE %:username%
            AND (:bookBefore IS NULL OR res.bookDate < :bookBefore)
            """)
    public Page<ReservationRowDTO> getRow(@Param("roomNumber") String roomNumber,
                                          @Param("username") String username,
                                          @Param("bookBefore")LocalDateTime bookBefore,
                                          Pageable pageable);

    @Query("""
            SELECT SUM(res.cost)
            FROM Reservation AS res
            WHERE (:month IS NULL OR MONTH(res.paymentDate) = :month)
            AND (:year IS NULL OR YEAR(res.paymentDate) = :year)
            """)
    public Double getTotalIncome(@Param("month") Long month,
                                 @Param("year") Long year);

    @Query("""
            SELECT new com.phoenix.phoenix.dto.reservation.ReservationDetailDTO(
            res.code,res.reservationMethod,res.roomNumber,ro.floor,ro.roomType
            ,res.guestUsername,CONCAT(gus.firstName,' ',gus.middleName,' ',gus.lastName),
            res.bookDate,res.checkIn,res.checkOut, res.cost, res.paymentDate,res.paymentMethod,
            res.remark
            )
            FROM Reservation AS res
                 JOIN Room AS ro ON ro.number = res.roomNumber
                 JOIN Guest AS gus ON res.guestUsername = gus.username
            WHERE res.code = :code     
            """)
    public ReservationDetailDTO getReservationDetail(@Param("code") String code);
}
