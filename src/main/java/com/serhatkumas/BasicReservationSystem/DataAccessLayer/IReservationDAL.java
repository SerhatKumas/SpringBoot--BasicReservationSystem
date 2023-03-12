package com.serhatkumas.BasicReservationSystem.DataAccessLayer;

import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IReservationDAL extends
        JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.customer_name=?1 AND r.reservation_date > ?2")
    Optional<Reservation> findReservationByCustomer_nameAndReservation_dateAfter(String customer_name, LocalDate localDate);
}
