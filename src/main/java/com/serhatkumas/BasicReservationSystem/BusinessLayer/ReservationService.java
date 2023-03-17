package com.serhatkumas.BasicReservationSystem.BusinessLayer;
import com.serhatkumas.BasicReservationSystem.DataAccessLayer.IReservationDAL;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
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

    public List<Reservation> getFutureReservations() {
        return reservationDAL.getReservationsAfterRequestedDate(LocalDate.now());
    }

    public List<Reservation> getPastReservations() {
        return reservationDAL.getReservationsBeforeRequestedDate(LocalDate.now());
    }

    public List<Reservation> getReservationsBetweenDates(LocalDate from, LocalDate until) {
        return reservationDAL.getReservationsBetweenDates(from, until);
    }

    public List<Reservation> getReservationsByDate(LocalDate date) {
        return reservationDAL.getReservationsByDate(date);
    }

    public void addNewReservation(Reservation reservation){
       Optional<Reservation> reservationByCustomerNameAndAfterToday = reservationDAL.findReservationByCustomer_nameAndReservation_dateAfter(reservation.getCustomer_name(), LocalDate.now());
        if(!LocalDate.now().isBefore(reservation.getReservation_date())){
            throw new IllegalStateException("Date can not be chosen before today.");
        }
        else if(reservationByCustomerNameAndAfterToday.isPresent()){
            throw new IllegalStateException("New reservation can not be added when another reservation is present.");
        }
        reservation.setReservation_code("NLF"+reservation.getCustomer_name().substring(0,2)+reservation.getReservation_date().getMonthValue()+reservation.getReservation_time().getHour()+ LocalTime.now().getSecond()+reservation.getReservation_date().getDayOfMonth());
        reservationDAL.save(reservation);
    }

    public void deleteReservation(String reservation_code){
        Optional<Reservation> reservation = reservationDAL.findReservationByReservation_code(reservation_code);
        if(reservation.isEmpty()){
            throw new IllegalStateException("Reservation with code " + reservation_code +" does not exists and can not be deleted.");
        }
        reservationDAL.deleteById(reservation.get().getReservation_id());
    }

    @Transactional
    public void updateReservation(String reservation_code, LocalDate date, LocalTime time) {
        Optional<Reservation> reservation = reservationDAL.findReservationByReservation_code(reservation_code);
        if(reservation.isEmpty()){
            throw new IllegalStateException("Reservation with code " + reservation_code +" does not exists and can not be updated.");
        }
        if( date != null && !Objects.equals(reservation.get().getReservation_date(),date)){
            reservation.get().setReservation_date(date);
        }

        if( time != null && !Objects.equals(reservation.get().getReservation_time(),time)){
            reservation.get().setReservation_time(time);
        }

    }
}
