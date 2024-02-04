package ru.teamscore.java23.hbooking;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.teamscore.java23.hbooking.entity.FareConditions;
import ru.teamscore.java23.hbooking.entity.Passenger;
import ru.teamscore.java23.hbooking.repository.BookingRepository;
import ru.teamscore.java23.hbooking.repository.FlightRepository;
import ru.teamscore.java23.hbooking.repository.TicketRepository;
import ru.teamscore.java23.hbooking.service.BookingManager;
import ru.teamscore.java23.hbooking.service.FlightManager;
import ru.teamscore.java23.hbooking.service.dto.FlightReservation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingMain {
    public static void main(String[] args) {
        EntityManagerFactory factory =  // JPA specific
                new Configuration() // Hibernate specific
                // Без этой строки по умолчанию настройки берутся из hibernate.properties
                .configure("hibernate-dev.cfg.xml")
                // Добавляем каждую сущность отдельно
                .addAnnotatedClass(ru.teamscore.java23.hbooking.entity.Flight.class)
                .addAnnotatedClass(ru.teamscore.java23.hbooking.entity.Airport.class)
                .addAnnotatedClass(ru.teamscore.java23.hbooking.entity.Ticket.class)
                .addAnnotatedClass(ru.teamscore.java23.hbooking.entity.TicketFlight.class)
                .addAnnotatedClass(ru.teamscore.java23.hbooking.entity.Booking.class)
                .buildSessionFactory();// Hibernate specific

        try (factory) {
            try (EntityManager entityManager = factory.createEntityManager()) {
                FlightManager flightManager = new FlightManager(new FlightRepository(entityManager));

                long flightsCount = flightManager.getFlightsCount();
                System.out.println("Всего рейсов в базе: " + flightsCount);

                System.out.println();
                var flightPG0404 = flightManager.getFlightShedule("PG0404");
                System.out.println(flightPG0404);

                var flights = flightManager.getAllFlights(0, 10);
                System.out.println("\nПервые 10 рейсов:");
                for (var flight : flights) {
                    System.out.println(flight);
                }

                System.out.println("Задержка рейса 116: " + flightManager.departure(116).toMinutes() + " минут");
            }

            try (EntityManager entityManager = factory.createEntityManager()) {
                BookingManager bookingManager = new BookingManager(new BookingRepository(entityManager),
                        new TicketRepository(entityManager));

                List<Passenger> passengers = new ArrayList<>();
                passengers.add(getPassenger("9999 000001", "IVAN TIMOFEEV", new HashMap<>()));
                passengers.add(getPassenger("9999 000002", "OLGA TIMOFEEVA", new HashMap<>()));
                passengers.add(getPassenger("9999 000003", "SEGREY TIMOFEEV", new HashMap<>()));

                List<FlightReservation> reservations = new ArrayList<>();
                reservations.add(new FlightReservation(26119, FareConditions.Comfort, BigDecimal.valueOf(10_000)));
                System.out.println(bookingManager.book(passengers, reservations));
            }
        }
    }

    private static Passenger getPassenger(String passengerId, String passengerName, Map<String, String> contactData) {
        var passenger = new Passenger();
        passenger.setPassengerId(passengerId);
        passenger.setPassengerName(passengerName);
        passenger.setContactData(contactData);
        return passenger;
    }
}
