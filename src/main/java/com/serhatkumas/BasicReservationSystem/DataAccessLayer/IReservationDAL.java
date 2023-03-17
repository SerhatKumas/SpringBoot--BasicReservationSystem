package com.serhatkumas.BasicReservationSystem.DataAccessLayer;

import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationDAL extends
        JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.reservation_date >= ?1")
    List<Reservation> getReservationsAfterRequestedDate(LocalDate date);

    @Query("SELECT r FROM Reservation r WHERE r.reservation_date <= ?1")
    List<Reservation> getReservationsBeforeRequestedDate(LocalDate date);

    @Query("SELECT r FROM Reservation r WHERE r.reservation_date > ?1 AND r.reservation_date < ?2")
    List<Reservation> getReservationsBetweenDates(LocalDate from, LocalDate until);

    @Query("SELECT r FROM Reservation r WHERE r.reservation_date = ?1")
    List<Reservation> getReservationsByDate(LocalDate date);

    @Query("Select r From Reservation r Where r.reservation_date=?1 AND r.reservation_time =?2")
    Optional<Reservation> getReservationByDateAndTime(LocalDate date, LocalTime time);

    @Query("SELECT r FROM Reservation r WHERE r.customer_name=?1 AND r.reservation_date > ?2")
    Optional<Reservation> findReservationByCustomer_nameAndReservation_dateAfter(String customer_name, LocalDate date);

    @Query("SELECT r FROM Reservation r WHERE r.reservation_code =?1")
    Optional<Reservation> findReservationByReservation_code(String reservation_id);

    //SELECT 'customer_name' FROM 'reservation' GROUP BY 'customer_name' ORDER BY COUNT(*) DESC LIMIT 1;
}
