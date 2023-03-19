package com.serhatkumas.BasicReservationSystem.DataAccessLayer;

import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

//Data Access Class (DAL, DAO, Repository Object)
@Repository
public interface IReservationDAL extends
        JpaRepository<Reservation, Long> {

    //Getting all reservations after a certain given date
    @Query("SELECT r FROM Reservation r WHERE r.reservation_date >= ?1")
    List<Reservation> getReservationsAfterRequestedDate(LocalDate date);

    //Getting all reservations before a certain given date
    @Query("SELECT r FROM Reservation r WHERE r.reservation_date <= ?1")
    List<Reservation> getReservationsBeforeRequestedDate(LocalDate date);

    //Getting all reservations between a certain given 2 dates
    @Query("SELECT r FROM Reservation r WHERE r.reservation_date > ?1 AND r.reservation_date < ?2")
    List<Reservation> getReservationsBetweenDates(LocalDate from, LocalDate until);

    //Getting all reservations on a certain given date
    @Query("SELECT r FROM Reservation r WHERE r.reservation_date = ?1")
    List<Reservation> getReservationsByDate(LocalDate date);

    //Getting all reservations of a customer by given customer name
    @Query("Select r From Reservation r Where r.customer_name=?1")
    List<Reservation> getAllReservationOfCustomerByName(String customer_name);

    //Getting rest of the reservations on current day with date and time parameters
    @Query("SELECT r FROM Reservation r WHERE r.reservation_date =?1 AND r.reservation_time>?2")
    List<Reservation> getRestOfTheReservationsToday(LocalDate date, LocalTime time);

    //Getting a reservation on a certain given date and time
    @Query("Select r From Reservation r Where r.reservation_date=?1 AND r.reservation_time =?2")
    Optional<Reservation> getReservationByDateAndTime(LocalDate date, LocalTime time);

    //Getting all reservations of a customer after certain date by customer name and date
    @Query("SELECT r FROM Reservation r WHERE r.customer_name=?1 AND r.reservation_date >=?2")
    Optional<Reservation> getReservationOfCustomerAfterCertainDateByNameAndDate(String customer_name, LocalDate date);

    //Getting a reservation by reservation code
    @Query("SELECT r FROM Reservation r WHERE r.reservation_code =?1")
    Optional<Reservation> getReservationByReservationCode(String reservation_code);

    //Getting number of all reservations on certain date
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.reservation_date =?1")
    int getHowManyCustomersOnCertainDate(LocalDate date);

    //Getting number of all reservations on rest of the certain date
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.reservation_date =?1 AND r.reservation_time>=?2")
    int getHowManyRemainingCustomersRestOfTheDay(LocalDate date, LocalTime time);

    //Getting list of all customer in descending order of visits they have made
    @Query("SELECT r.customer_name FROM Reservation r GROUP BY r.customer_name ORDER BY COUNT(r) DESC")
    List<String> getCustomerListInOrderOfNumberOfVisit();

}
