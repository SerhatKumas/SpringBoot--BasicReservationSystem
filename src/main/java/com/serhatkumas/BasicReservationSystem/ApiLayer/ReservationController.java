package com.serhatkumas.BasicReservationSystem.ApiLayer;

import com.serhatkumas.BasicReservationSystem.BusinessLayer.ReservationService;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping (path = "show-customers")
    public List<Reservation> getAllReservations(){

        return reservationService.getAllReservations();
    }

}
