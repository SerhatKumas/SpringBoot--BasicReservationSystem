package com.serhatkumas.BasicReservationSystem.ApiLayer;

import com.serhatkumas.BasicReservationSystem.BusinessLayer.ReservationService;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "reservation-api")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(path = "show-customers")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @PostMapping(path = "new-reservation")
    public void createNewReservation(@RequestBody Reservation reservation){
        reservationService.addNewReservation(reservation);
    }

}
