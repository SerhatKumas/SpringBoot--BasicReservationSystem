package com.serhatkumas.BasicReservationSystem.BusinessLayer;
import com.serhatkumas.BasicReservationSystem.DataAccessLayer.IReservationDAL;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public void addNewReservation(Reservation reservation){
       Optional<Reservation> reservationByCustomerNameAndAfterToday = reservationDAL.findReservationByCustomer_nameAndReservation_dateAfter(reservation.getCustomer_name(), LocalDate.now());
        if(!LocalDate.now().isBefore(reservation.getReservation_date())){
            throw new IllegalStateException("Date can not be chosen before today");
        }
        else if(reservationByCustomerNameAndAfterToday.isPresent()){
            throw new IllegalStateException("New reservation can not be added when another reservation is present");
        }
        reservationDAL.save(reservation);
    }

}
