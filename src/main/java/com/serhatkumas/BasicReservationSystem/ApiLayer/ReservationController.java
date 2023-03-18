package com.serhatkumas.BasicReservationSystem.ApiLayer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serhatkumas.BasicReservationSystem.BusinessLayer.ReservationService;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "reservation-api")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping()
    public String showWelcomeMessage(){
        return "Welcome to Reservation Api, enter url to reach end point you want";
    }

    @GetMapping(path = "show-all-reservations")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @GetMapping(path = "show-future-reservations")
    public List<Reservation> getFutureReservations(){
        return reservationService.getFutureReservations();
    }

    @GetMapping(path = "show-past-reservations")
    public List<Reservation> getPastReservations(){
        return reservationService.getPastReservations();
    }

    @GetMapping(path = "show-reservations-between")
    public List<Reservation> getReservationsBetweenDates(
            @RequestBody String query_info){

        JSONObject json = null;
        LocalDate from = null;
        LocalDate until = null;
        try {
            json = new JSONObject(query_info);
            from = LocalDate.parse(json.getString("from"));
            until = LocalDate.parse(json.getString("until"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
         return reservationService.getReservationsBetweenDates(from, until);
    }

    @GetMapping(path = "show-reservations-date")
    public List<Reservation> getReservationsByDate(@RequestBody String query_info){
        JSONObject json = null;
        LocalDate date = null;
        try {
            json = new JSONObject(query_info);
            date = LocalDate.parse(json.getString("date"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getReservationsByDate(date);
    }

    @GetMapping(path = "/show-all-reservations-by-name")
    public List<Reservation> getAllReservationOfCustomerByName( @RequestBody String query_info){
        JSONObject json = null;
        String customer_name = "";
        try{
           json = new JSONObject(query_info);
           customer_name = json.getString("customer_name");
        }
        catch(JSONException e){
            throw new RuntimeException(e);
        }
       return reservationService.getAllReservationOfCustomerByName(customer_name);
    }

    @GetMapping(path = "show-reservations-of-today")
    public List<Reservation> getReservationsOfToday(){
        return reservationService.getReservationsOfToday();
    }

    @GetMapping(path = "show-remaining-reservations-today")
    public List<Reservation> getRestOfTheReservationsToday(){
        return reservationService.getRestOfTheReservationsToday();
    }

    @GetMapping(path = "/show-active-reservations-by-name")
    public Optional<Reservation> getActiveReservationOfCustomerByName( @RequestBody String query_info){
        JSONObject json = null;
        String customer_name = "";
        try{
            json = new JSONObject(query_info);
            customer_name = json.getString("customer_name");
        }
        catch(JSONException e){
            throw new RuntimeException(e);
        }
        return reservationService.getActiveReservationOfCustomerByName(customer_name);
    }

    @GetMapping(path = "/show-active-reservations-after-certain-date-by-name-date")
    public Reservation getActiveReservationOfCustomerAfterCertainDateByNameAndDate( @RequestBody String query_info){
        JSONObject json = null;
        String customer_name = "";
        LocalDate date = null;
        try{
            json = new JSONObject(query_info);
            customer_name = json.getString("customer_name");
            date = LocalDate.parse(json.getString("date"));
        }
        catch(JSONException e){
            throw new RuntimeException(e);
        }
        return reservationService.getActiveReservationOfCustomerAfterCertainDateByNameAndDate(customer_name, date);
    }

    @GetMapping(path = "show-reservations-date-time")
    public Optional<Reservation> getReservationByDateAndTime(@RequestBody String query_info){
        JSONObject json = null;
        LocalDate date = null;
        LocalTime time = null;
        try {
            json = new JSONObject(query_info);
            date = LocalDate.parse(json.getString("date"));
            time = LocalTime.parse(json.getString("time"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getReservationByDateAndTime(date, time);
    }

    @PostMapping(path = "create-reservation")
    public void addNewReservation(@RequestBody Reservation reservation){
        reservationService.addNewReservation(reservation);
    }

    @DeleteMapping(path = "delete-reservation")
    public void deleteReservation(@RequestBody String query_info){
        JSONObject json = null;
        String reservation_code = "";
        try {
            json = new JSONObject(query_info);
            reservation_code = json.getString("reservation_code");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        reservationService.deleteReservation(reservation_code);
    }

    @PutMapping(path = "update-reservation")
    public void updateReservation(
            @RequestBody String query_info){

        JSONObject json = null;
        String reservation_code = "";
        LocalDate date = null;
        LocalTime time = null;
        try {
            json = new JSONObject(query_info);
            reservation_code = json.getString("reservation_code");
            date = LocalDate.parse(json.getString("date"));
            time = LocalTime.parse(json.getString("time"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        reservationService.updateReservation(reservation_code, date, time);
    }

    @GetMapping(path = "how-many-customers-date")
    public int getHowManyCustomersOnCertainDate( @RequestBody String query_info){
        JSONObject json = null;
        LocalDate date = null;
        try {
            json = new JSONObject(query_info);
            date = LocalDate.parse(json.getString("date"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getHowManyCustomersOnCertainDate(date);
    }

    @GetMapping(path = "how-many-customers-today")
    public int getHowManyCustomersOnCertainDate(){
        return reservationService.getHowManyCustomersToday();
    }

    @GetMapping(path = "how-many-remaining-customers-today")
    public int getHowManyRemainingCustomersRestOfToday(){
        return reservationService.getHowManyRemainingCustomersRestOfToday();
    }

    @GetMapping(path = "show-total-number-customer")
    public int getNumberOfTotalCustomer(){
        return reservationService.getNumberOfTotalCustomer();
    }

    @GetMapping(path = "show-best-customer")
    public String getBestCustomerByRank(@RequestBody String query_info){
        JSONObject json = null;
        int rank = 0;
        try {
            json = new JSONObject(query_info);
            rank = Integer.parseInt(json.getString("rank")) - 1;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getBestCustomerByRank(rank);
    }

}
