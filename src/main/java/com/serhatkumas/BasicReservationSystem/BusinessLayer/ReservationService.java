package com.serhatkumas.BasicReservationSystem.BusinessLayer;
import com.serhatkumas.BasicReservationSystem.DataAccessLayer.IReservationDAL;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final IReservationDAL reservationDAL;

    @Autowired
    public ReservationService(IReservationDAL reservationDAL) {
        this.reservationDAL = reservationDAL;
    }

    public List<Reservation> getAllReservations(){
        return reservationDAL.findAll();
    }

}
