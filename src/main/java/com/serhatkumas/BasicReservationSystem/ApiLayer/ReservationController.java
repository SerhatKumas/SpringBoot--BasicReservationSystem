package com.serhatkumas.BasicReservationSystem.ApiLayer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serhatkumas.BasicReservationSystem.BusinessLayer.ReservationService;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "reservation-api")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
            @RequestBody String dateInfo){

        JSONObject json = null;
        String from = "";
        String until = "";
        try {
            json = new JSONObject(dateInfo);
            from = json.getString("from");
            until = json.getString("until");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        LocalDate dateFrom = LocalDate.parse(from);
        LocalDate dateUntil = LocalDate.parse(until);
         return reservationService.getReservationsBetweenDates(dateFrom, dateUntil);
    }



    @PostMapping(path = "create-reservation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewReservation(@RequestBody Reservation reservation){
        reservationService.addNewReservation(reservation);
    }

    @DeleteMapping(path = "delete-reservation")
    public void deleteReservation(@RequestBody String reservation_code){
        reservationService.deleteReservation(reservation_code);
    }

    @PutMapping(path = "update-reservation")
    public void updateReservation(
            @RequestBody String reservation_code, LocalDate date, LocalTime time){
        reservationService.updateReservation(reservation_code, date, time);
    }

}
