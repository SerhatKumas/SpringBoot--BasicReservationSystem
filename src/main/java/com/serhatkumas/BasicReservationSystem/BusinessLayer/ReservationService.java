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

//Business Logic class (Business Layer)
@Service
public class ReservationService {

    //Reservation data access class instance
    private final IReservationDAL reservationDAL;

    //Constructor injection of reservation data access class for delegation
    @Autowired
    public ReservationService(IReservationDAL reservationDAL) {
        this.reservationDAL = reservationDAL;
    }

    //Getting all reservations
    public List<Reservation> getAllReservations() {
        return reservationDAL.findAll();
    }

    //Getting all future reservation from current date
    public List<Reservation> getFutureReservations() {
        return reservationDAL.getReservationsAfterRequestedDate(LocalDate.now());
    }

    //Getting all past reservation from current date
    public List<Reservation> getPastReservations() {
        return reservationDAL.getReservationsBeforeRequestedDate(LocalDate.now());
    }

    //Getting all reservation between 2 given dates
    public List<Reservation> getReservationsBetweenDates(LocalDate from, LocalDate until) {
        return reservationDAL.getReservationsBetweenDates(from, until);
    }

    //Getting reservation on a certain date
    public List<Reservation> getReservationsByDate(LocalDate date) {
        return reservationDAL.getReservationsByDate(date);
    }

    //Getting all reservation of a customer by customer name
    public List<Reservation> getAllReservationOfCustomerByName(String customer_name) {
        return reservationDAL.getAllReservationOfCustomerByName(customer_name);
    }

    //Getting all reservation on a current date
    public List<Reservation> getReservationsOfToday() {
        return reservationDAL.getReservationsByDate(LocalDate.now());
    }

    //Getting rest of the reservations on current date with the time parameter
    //If time is 3.00 pm, it will show you the reservations of a current date after 3.00 pm
    public List<Reservation> getRestOfTheReservationsToday() {
        return reservationDAL.getRestOfTheReservationsToday(LocalDate.now(), LocalTime.now());
    }

    //Getting all not-expired reservations of a customer
    public Optional<Reservation> getActiveReservationOfCustomerByName(String customer_name) {
        return reservationDAL.getReservationOfCustomerAfterCertainDateByNameAndDate(customer_name, LocalDate.now());
    }

    //Getting all not-expired reservations of a customer after certain date
    public Reservation getActiveReservationOfCustomerAfterCertainDateByNameAndDate(String customer_name, LocalDate date) {
        Optional<Reservation> reservation = reservationDAL.getReservationOfCustomerAfterCertainDateByNameAndDate(customer_name, date);
        if (reservation.isPresent()) {
            return reservation.get();
        } else {
            throw new IllegalStateException("Reservation belongs to " + customer_name + " on " + date + " does not exists.");
        }
    }

    //Getting a certain reservation on given date and time
    public Optional<Reservation> getReservationByDateAndTime(LocalDate date, LocalTime time) {
        return reservationDAL.getReservationByDateAndTime(date, time);
    }

    //Adding a new reservation
    public void addNewReservation(Reservation reservation) {
        Optional<Reservation> reservationByCustomerNameAndAfterToday = reservationDAL.getReservationOfCustomerAfterCertainDateByNameAndDate(reservation.getCustomer_name(), LocalDate.now());
        //First, we check whether taken date and time is expired or not (whether before current time or not)
        if (LocalDate.now().isAfter(reservation.getReservation_date()) || (LocalDate.now().isEqual(reservation.getReservation_date()) && LocalTime.now().isAfter(reservation.getReservation_time()))) {
            throw new IllegalStateException("Date can not be chosen before today.");
        }
        //Second, we check whether customer has active reservation or not.
        //If they have, new reservation is not given. If not reservation can be created.
        else if (reservationByCustomerNameAndAfterToday.isPresent()) {
            throw new IllegalStateException("New reservation can not be added when another reservation is present.");
        }
        //New reservation crated and issued with reservation
        reservation.setReservation_code("NLF" + reservation.getCustomer_name().substring(0, 2) + reservation.getReservation_date().getMonthValue() + reservation.getReservation_time().getHour() + LocalTime.now().getSecond() + reservation.getReservation_date().getDayOfMonth());
        reservationDAL.save(reservation);
    }

    //Deleting a reservation with unique reservation code
    public void deleteReservation(String reservation_code) {
        //Checking whether reservation with given reservation code is existed or not
        Optional<Reservation> reservation = reservationDAL.getReservationByReservationCode(reservation_code);
        if (reservation.isEmpty()) {
            throw new IllegalStateException("Reservation with code " + reservation_code + " does not exists and can not be deleted.");
        }
        //Deleting reservation with reservation id
        reservationDAL.deleteById(reservation.get().getReservation_id());
    }

    //Updating reservation time or date with reservation code
    @Transactional
    public void updateReservation(String reservation_code, LocalDate date, LocalTime time) {
        Optional<Reservation> reservation = reservationDAL.getReservationByReservationCode(reservation_code);
        //Checking whether reservation with given reservation code is existed or not
        if (reservation.isEmpty()) {
            throw new IllegalStateException("Reservation with code " + reservation_code + " does not exists and can not be updated.");
        }
        //If date info taken from user is not empty and different from the date info in the db, we update the date parameter of a reservation
        if (date != null && !Objects.equals(reservation.get().getReservation_date(), date)) {
            reservation.get().setReservation_date(date);
        }
        //If time info taken from user is not empty and different from the time info in the db, we update the time parameter of a reservation
        if (time != null && !Objects.equals(reservation.get().getReservation_time(), time)) {
            reservation.get().setReservation_time(time);
        }

    }

    //Getting number of all reservations on the certain date
    public int getHowManyCustomersOnCertainDate(LocalDate date) {
        return reservationDAL.getHowManyCustomersOnCertainDate(date);
    }

    //Getting number of all reservations on the current date
    public int getHowManyCustomersToday() {
        return reservationDAL.getHowManyCustomersOnCertainDate(LocalDate.now());
    }

    //Getting number of all reservations on rest of the certain date with time parameter
    public int getHowManyRemainingCustomersRestOfToday() {
        return reservationDAL.getHowManyRemainingCustomersRestOfTheDay(LocalDate.now(), LocalTime.now());
    }

    //Getting nth best customer according to number of visit they have done
    public String getBestCustomerByRank(int order) {
        List<String> reservationList = reservationDAL.getCustomerListInOrderOfNumberOfVisit();
        String customer_name = reservationList.get(order);
        if (customer_name != null) {
            return "Name : " + customer_name;
        }
        throw new IllegalStateException("There is no customer who has visited you store");
    }

    //Getting number of customer from the beginning (Not The number of visitation, number of different customers)
    public int getNumberOfTotalCustomer() {
        return reservationDAL.getCustomerListInOrderOfNumberOfVisit().size();
    }
}
