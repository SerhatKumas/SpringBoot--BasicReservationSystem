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

    public List<Reservation> getAllReservationOfCustomerByName(String customer_name){
        return reservationDAL.getAllReservationOfCustomerByName(customer_name);
    }

    public List<Reservation> getReservationsOfToday(){
        return reservationDAL.getReservationsByDate(LocalDate.now());
    }

    public List<Reservation> getRestOfTheReservationsToday(){
        return reservationDAL.getRestOfTheReservationsToday(LocalDate.now(), LocalTime.now());
    }

    public Optional<Reservation> getActiveReservationOfCustomerByName(String customer_name){
        return reservationDAL.getActiveReservationOfCustomerAfterCertainDateByNameAndDate(customer_name, LocalDate.now());
    }

    public Reservation getActiveReservationOfCustomerAfterCertainDateByNameAndDate(String customer_name, LocalDate date){
        Optional<Reservation> reservation =  reservationDAL.getActiveReservationOfCustomerAfterCertainDateByNameAndDate(customer_name, date);
        if(reservation.isPresent()){
            return reservation.get();
        }
        else{
            throw new IllegalStateException("Reservation belongs to " + customer_name +" on " + date +" does not exists.");
        }
    }

    public Optional<Reservation> getReservationByDateAndTime(LocalDate date, LocalTime time) {
        return reservationDAL.getReservationByDateAndTime(date, time);
    }

    public void addNewReservation(Reservation reservation){
       Optional<Reservation> reservationByCustomerNameAndAfterToday = reservationDAL.getActiveReservationOfCustomerAfterCertainDateByNameAndDate(reservation.getCustomer_name(), LocalDate.now());
        if(LocalDate.now().isAfter(reservation.getReservation_date()) || (LocalDate.now().isEqual(reservation.getReservation_date()) && LocalTime.now().isAfter(reservation.getReservation_time())) ){
            throw new IllegalStateException("Date can not be chosen before today.");
        }
        else if(reservationByCustomerNameAndAfterToday.isPresent()){
            throw new IllegalStateException("New reservation can not be added when another reservation is present.");
        }
        reservation.setReservation_code("NLF"+reservation.getCustomer_name().substring(0,2)+reservation.getReservation_date().getMonthValue()+reservation.getReservation_time().getHour()+ LocalTime.now().getSecond()+reservation.getReservation_date().getDayOfMonth());
        reservationDAL.save(reservation);
    }

    public void deleteReservation(String reservation_code){
        Optional<Reservation> reservation = reservationDAL.getReservationByReservationCode(reservation_code);
        if(reservation.isEmpty()){
            throw new IllegalStateException("Reservation with code " + reservation_code +" does not exists and can not be deleted.");
        }
        reservationDAL.deleteById(reservation.get().getReservation_id());
    }

    @Transactional
    public void updateReservation(String reservation_code, LocalDate date, LocalTime time) {
        Optional<Reservation> reservation = reservationDAL.getReservationByReservationCode(reservation_code);
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

    public int getHowManyCustomersOnCertainDate(LocalDate date){
        return reservationDAL.getHowManyCustomersOnCertainDate(date);
    }

    public int getHowManyCustomersToday() {
        return reservationDAL.getHowManyCustomersOnCertainDate(LocalDate.now());
    }

    public int getHowManyRemainingCustomersRestOfToday() {
        return reservationDAL.getHowManyRemainingCustomersRestOfToday(LocalDate.now(), LocalTime.now());
    }

    public String getBestCustomerByRank(int order) {
        List<String> reservationList = reservationDAL.getCustomerListInOrderOfNumberOfVisit();
        String customer_name = reservationList.get(order);
        if (customer_name != null){
            return "Name : " + customer_name;
        }
        throw new IllegalStateException("There is no customer who has visited you store");
    }

    public int getNumberOfTotalCustomer() {
        return reservationDAL.getCustomerListInOrderOfNumberOfVisit().size();
    }
}
