package com.serhatkumas.BasicReservationSystem.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
public class Reservation {
    @Id
    @SequenceGenerator(
            name = "reservation",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long reservation_id;

    private LocalDate reservation_date;

    private LocalTime reservation_time;

    private String customer_name;

    private String customer_phone_number;

    private int customer_age;

    private String reservation_code;

    public Reservation() {
    }

    public Reservation(Long reservation_id, LocalDate reservation_date, LocalTime reservation_time, String customer_name, String customer_phone_number, int customer_age, String reservation_code) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.reservation_time = reservation_time;
        this.customer_name = customer_name;
        this.customer_phone_number = customer_phone_number;
        this.customer_age = customer_age;
        this.reservation_code = reservation_code;
    }

    public Reservation(LocalDate reservation_date, LocalTime reservation_time, String customer_name, String customer_phone_number, int customer_age, String reservation_code) {
        this.reservation_date = reservation_date;
        this.reservation_time = reservation_time;
        this.customer_name = customer_name;
        this.customer_phone_number = customer_phone_number;
        this.customer_age = customer_age;
        this.reservation_code = reservation_code;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public LocalDate getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDate reservation_date) {
        this.reservation_date = reservation_date;
    }

    public LocalTime getReservation_time() {
        return reservation_time;
    }

    public void setReservation_time(LocalTime reservation_time) {
        this.reservation_time = reservation_time;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone_number() {
        return customer_phone_number;
    }

    public void setCustomer_phone_number(String customer_phone_number) {
        this.customer_phone_number = customer_phone_number;
    }

    public int getCustomer_age() {
        return customer_age;
    }

    public void setCustomer_age(int customer_age) {
        this.customer_age = customer_age;
    }

    public String getReservation_code() {
        return reservation_code;
    }

    public void setReservation_code(String reservation_code) {
        this.reservation_code = reservation_code;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", reservation_date=" + reservation_date +
                ", reservation_time=" + reservation_time +
                ", customer_name='" + customer_name + '\'' +
                ", customer_phone_number='" + customer_phone_number + '\'' +
                ", customer_age=" + customer_age +
                ", reservation_code='" + reservation_code + '\'' +
                '}';
    }
}
