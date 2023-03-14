package com.serhatkumas.BasicReservationSystem.Model;


import com.serhatkumas.BasicReservationSystem.DataAccessLayer.IReservationDAL;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@Configuration
public class ReservationConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            IReservationDAL reservationDAL){
        return args -> {
                Reservation r1 = new Reservation("NLFSe3152520",
                        LocalDate.of(2023, Month.MARCH, 20),
                        LocalTime.of(15,45),"Serhat Kumas","05516217943",
                        6
                );

            Reservation r2 = new Reservation("NLFSa3202510",
                    LocalDate.of(2023, Month.MARCH, 10),
                    LocalTime.of(20,0),"Sabri Tan","05515677943",
                    23
            );

            Reservation r3 = new Reservation("NLFGo3112515",
                    LocalDate.of(2023, Month.MARCH, 15),
                    LocalTime.of(11,15),"Gokay Cevik","05513457943",
                    45
            );
            Reservation r4 = new Reservation("NLFSe7152520",
                    LocalDate.of(2023, Month.JULY, 20),
                    LocalTime.of(15,45),"Serhat Kumas","05516217943",
                    6
            );
            Reservation r5 = new Reservation("NLFBi1152520",
                    LocalDate.of(2023, Month.JANUARY, 20),
                    LocalTime.of(15,45),"Bilgehan Savgu","05116217943",
                    18
            );

            Reservation r6 = new Reservation("NLFTa4202510",
                    LocalDate.of(2023, Month.APRIL, 10),
                    LocalTime.of(20,0),"Taha Bilgehan Cici","05315677943",
                    89
            );

            Reservation r7 = new Reservation("NLFAn10112515",
                    LocalDate.of(2023, Month.OCTOBER, 15),
                    LocalTime.of(11,15),"Anil Guler","05213457943",
                    20
            );
            Reservation r8 = new Reservation("NLFBi12152520",
                    LocalDate.of(2023, Month.DECEMBER, 20),
                    LocalTime.of(15,45),"Bilgehan Savgu","05716217943",
                    18
            );


            reservationDAL.saveAll(List.of(r1,r2,r3,r4,r5,r6,r7,r8));
        };
    }

}
