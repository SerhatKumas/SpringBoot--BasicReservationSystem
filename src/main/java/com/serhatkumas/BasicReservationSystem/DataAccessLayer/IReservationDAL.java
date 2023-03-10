package com.serhatkumas.BasicReservationSystem.DataAccessLayer;

import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationDAL extends
        JpaRepository<Reservation, Long> {
}
