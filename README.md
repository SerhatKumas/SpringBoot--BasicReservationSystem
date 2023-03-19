# SpringBoot--BasicReservationSystem


- It is a basic REST Api project for reservation management system while learning about spring boot


- Currently, 1 api file, 1 business rules file and 1 data access file are existed (Api - Service - Data Access Layers)


- Customer and admin queries specified in comments in Api file


- ReservationConfig file contains dummy data for usage in Postgres, Db records are created and loaded into db everytime project is executed 


- Reservation file contains our reservation entity structure, its constructors and getter , setter methods


- IReservationalDAL is data access interface that extends JpaRepository and contains unique querries


- ReservationService contains business rules of our system and delegates tasks to IReservationalDal


- ReservationService contains end points of our system and data parsing from json to requested typed


## End Points

- Welcome message for -> url/reservation-api -> Customer endpoint


- Getting all reservations for -> url/show-all-reservations -> Admin endpoint


- Getting all future reservation from current date for -> url/show-future-reservations -> Admin endpoint


- Getting all past reservation from current date for -> url/show-past-reservations -> Admin endpoint


- Getting all reservation between 2 given dates for -> url/show-reservations-between -> Admin endpoint


- Getting reservation on a certain date for -> url/show-reservations-date -> Admin endpoint


- Getting all reservation of a customer by customer name for -> url/show-all-reservations-by-name -> Admin endpoint


- Getting all reservation on a current date for -> url/show-reservations-of-today -> Admin endpoint


- Getting rest of the reservations on current date with the time parameter for -> url/show-remaining-reservations-today -> Admin endpoint


- Getting reservation details by customer name and reservation code for -> url/show-reservations-by-name-and-reservation-code -> Customer endpoint


- Getting all not-expired reservations of a customer for -> url/show-active-reservations-by-name -> Admin endpoint


- Getting all not-expired reservations of a customer after certain date for -> url/show-active-reservations-after-certain-date-by-name-date -> Admin endpoint


- Getting a certain reservation on given date and time for -> url/show-reservation-date-time -> Admin endpoint


- Adding a new reservation for -> url/create-reservation -> Customer endpoint


- Deleting a reservation for -> url/delete-reservation -> Customer endpoint


- Updating a reservation for -> url/update-reservation -> Customer endpoint


- Getting number of all reservations on the certain date for -> url/how-many-customers-date -> Admin endpoint


- Getting number of all reservations on the current date for -> url/how-many-customers-today -> Admin endpoint


- Getting number of all reservations on rest of the certain date with time parameter for -> url/how-many-remaining-customers-today -> Admin endpoint


- Getting number of customer from the beginning (Not The number of visitation, number of different customers) for -> url/show-total-number-customer -> Admin endpoint


- Getting nth best customer according to number of visit they have done for -> url/show-nth-best-customer -> Admin endpoint


## Technologies

- [Java](https://www.java.com/tr/)

- [Spring Framework](https://spring.io/)

- [Spring Data Jpa Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

- [Hibernate](https://hibernate.org)

- [PostgreSQL](https://www.postgresql.org)

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/)

- [Postman](https://www.postman.com)


## Test

- git clone https://github.com/SerhatKumas/SpringBoot--BasicReservationSystem.git

- Download dependencies

- Configure database properties from application.properties

- Run Application

- By using browser or postman you can test end points


### Reference Documentation

- [Spring Boot Tutorial / Amigoscode](https://www.youtube.com/watch?v=9SGDpanrc8U&t=22s)


# Hi, I'm Serhat the Author! 👋

## 🔗 Connection Links

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/serhatkumas/)

[![@Github](https://img.shields.io/badge/github-0A66C2?style=for-the-badge&logo=github&logoColor=white)](https://www.github.com/serhatkumas)
