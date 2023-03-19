package com.serhatkumas.BasicReservationSystem.ApiLayer;

import com.serhatkumas.BasicReservationSystem.BusinessLayer.ReservationService;
import com.serhatkumas.BasicReservationSystem.Model.Reservation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

//End-point class for users
//All request information is taken from request body with json data type
@RestController
@RequestMapping(path = "reservation-api")
public class ReservationController {

    //Reservation business rules class instance
    private final ReservationService reservationService;

    //Constructor injection of reservation business rules class for delegation
    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //Welcome message for -> url/reservation-api -> Customer endpoint
    @GetMapping
    public String showWelcomeMessage() {
        return "Welcome to Reservation Api, enter url to reach end point you want";
    }

    //Getting all reservations for -> url/show-all-reservations -> Admin endpoint
    @GetMapping(path = "show-all-reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    //Getting all future reservation from current date for -> url/show-future-reservations -> Admin endpoint
    @GetMapping(path = "show-future-reservations")
    public List<Reservation> getFutureReservations() {
        return reservationService.getFutureReservations();
    }

    //Getting all past reservation from current date for -> url/show-past-reservations -> Admin endpoint
    @GetMapping(path = "show-past-reservations")
    public List<Reservation> getPastReservations() {
        return reservationService.getPastReservations();
    }

    //Getting all reservation between 2 given dates for -> url/show-reservations-between -> Admin endpoint
    @GetMapping(path = "show-reservations-between")
    public List<Reservation> getReservationsBetweenDates(
            @RequestBody String query_info) {
        JSONObject json;
        LocalDate from;
        LocalDate until;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            from = LocalDate.parse(json.getString("from"));
            until = LocalDate.parse(json.getString("until"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getReservationsBetweenDates(from, until);
    }

    //Getting reservation on a certain date for -> url/show-reservations-date -> Admin endpoint
    @GetMapping(path = "show-reservations-date")
    public List<Reservation> getReservationsByDate(@RequestBody String query_info) {
        JSONObject json;
        LocalDate date;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            date = LocalDate.parse(json.getString("date"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getReservationsByDate(date);
    }

    //Getting all reservation of a customer by customer name for -> url/show-all-reservations-by-name -> Admin endpoint
    @GetMapping(path = "/show-all-reservations-by-name")
    public List<Reservation> getAllReservationOfCustomerByName(@RequestBody String query_info) {
        JSONObject json;
        String customer_name;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            customer_name = json.getString("customer_name");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getAllReservationOfCustomerByName(customer_name);
    }

    //Getting all reservation on a current date for -> url/show-reservations-of-today -> Admin endpoint
    @GetMapping(path = "show-reservations-of-today")
    public List<Reservation> getReservationsOfToday() {
        return reservationService.getReservationsOfToday();
    }

    //Getting rest of the reservations on current date with the time parameter for -> url/show-remaining-reservations-today -> Admin endpoint
    @GetMapping(path = "show-remaining-reservations-today")
    public List<Reservation> getRestOfTheReservationsToday() {
        return reservationService.getRestOfTheReservationsToday();
    }

    //Getting reservation details by customer name and reservation code for -> url/show-reservations-by-name-and-reservation-code -> Customer endpoint
    @GetMapping(path = "/show-reservations-by-name-and-reservation-code")
    public Optional<Reservation> getReservationByReservationCodeAndCustomerName(@RequestBody String query_info) {
        JSONObject json;
        String customer_name;
        String reservation_code;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            customer_name = json.getString("customer_name");
            reservation_code = json.getString("reservation_code");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getReservationByReservationCodeAndCustomerName(customer_name, reservation_code);
    }

    //Getting all not-expired reservations of a customer for -> url/show-active-reservations-by-name -> Admin endpoint
    @GetMapping(path = "/show-active-reservation-by-name")
    public Optional<Reservation> getActiveReservationOfCustomerByName(@RequestBody String query_info) {
        JSONObject json;
        String customer_name;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            customer_name = json.getString("customer_name");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getActiveReservationOfCustomerByName(customer_name);
    }

    //Getting all not-expired reservations of a customer after certain date for -> url/show-active-reservations-after-certain-date-by-name-date -> Admin endpoint
    @GetMapping(path = "/show-active-reservation-after-certain-date-by-name-date")
    public Reservation getActiveReservationOfCustomerAfterCertainDateByNameAndDate(@RequestBody String query_info) {
        JSONObject json;
        String customer_name;
        LocalDate date;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            customer_name = json.getString("customer_name");
            date = LocalDate.parse(json.getString("date"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getActiveReservationOfCustomerAfterCertainDateByNameAndDate(customer_name, date);
    }

    //Getting a certain reservation on given date and time for -> url/show-reservation-date-time -> Admin endpoint
    @GetMapping(path = "show-reservation-date-time")
    public Optional<Reservation> getReservationByDateAndTime(@RequestBody String query_info) {
        JSONObject json;
        LocalDate date;
        LocalTime time;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            date = LocalDate.parse(json.getString("date"));
            time = LocalTime.parse(json.getString("time"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getReservationByDateAndTime(date, time);
    }

    //Adding a new reservation for -> url/create-reservation -> Customer endpoint
    @PostMapping(path = "create-reservation")
    public void addNewReservation(@RequestBody Reservation reservation) {
        reservationService.addNewReservation(reservation);
    }

    //Deleting a reservation for -> url/delete-reservation -> Customer endpoint
    @DeleteMapping(path = "delete-reservation")
    public void deleteReservation(@RequestBody String query_info) {
        JSONObject json;
        String reservation_code;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            reservation_code = json.getString("reservation_code");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        reservationService.deleteReservation(reservation_code);
    }

    //Updating a reservation for -> url/update-reservation -> Customer endpoint
    @PutMapping(path = "update-reservation")
    public void updateReservation(
            @RequestBody String query_info) {

        JSONObject json;
        String reservation_code;
        LocalDate date;
        LocalTime time;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            reservation_code = json.getString("reservation_code");
            date = LocalDate.parse(json.getString("date"));
            time = LocalTime.parse(json.getString("time"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        reservationService.updateReservation(reservation_code, date, time);
    }

    //Getting number of all reservations on the certain date for -> url/how-many-customers-date -> Admin endpoint
    @GetMapping(path = "how-many-customers-date")
    public int getHowManyCustomersOnCertainDate(@RequestBody String query_info) {
        JSONObject json;
        LocalDate date;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            date = LocalDate.parse(json.getString("date"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getHowManyCustomersOnCertainDate(date);
    }

    //Getting number of all reservations on the current date for -> url/how-many-customers-today -> Admin endpoint
    @GetMapping(path = "how-many-customers-today")
    public int getHowManyCustomersOnCertainDate() {
        return reservationService.getHowManyCustomersToday();
    }

    //Getting number of all reservations on rest of the certain date with time parameter for -> url/how-many-remaining-customers-today -> Admin endpoint
    @GetMapping(path = "how-many-remaining-customers-today")
    public int getHowManyRemainingCustomersRestOfToday() {
        return reservationService.getHowManyRemainingCustomersRestOfToday();
    }

    //Getting number of customer from the beginning (Not The number of visitation, number of different customers) for -> url/show-total-number-customer -> Admin endpoint
    @GetMapping(path = "show-total-number-customer")
    public int getNumberOfTotalCustomer() {
        return reservationService.getNumberOfTotalCustomer();
    }

    //Getting nth best customer according to number of visit they have done for -> url/show-nth-best-customer -> Admin endpoint
    @GetMapping(path = "show-nth-best-customer")
    public String getBestCustomerByRank(@RequestBody String query_info) {
        JSONObject json;
        int rank;
        try {
            //Taking json data from request body
            json = new JSONObject(query_info);
            //Parsing required parameters
            rank = Integer.parseInt(json.getString("rank")) - 1;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return reservationService.getBestCustomerByRank(rank);
    }

}
