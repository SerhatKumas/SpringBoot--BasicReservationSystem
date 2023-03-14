package com.serhatkumas.BasicReservationSystem.ApiLayer;

import com.serhatkumas.BasicReservationSystem.BusinessLayer.ReservationService;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "show-reservation")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @PostMapping(path = "create-reservation")
    public void createNewReservation(@RequestBody Reservation reservation){
        reservationService.addNewReservation(reservation);
    }

    @DeleteMapping(path = "delete-reservation/{reservation-code}")
    public void deleteReservation(@PathVariable("reservation-code") String reservation_code){
        reservationService.deleteReservation(reservation_code);
    }

    @PutMapping(path = "update-reservation/{reservation-code}")
    public void updateReservation(
            @PathVariable("reservation-code") String reservation_code,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false)LocalTime time
            ){
        reservationService.updateReservation(reservation_code, date, time);
    }

}
