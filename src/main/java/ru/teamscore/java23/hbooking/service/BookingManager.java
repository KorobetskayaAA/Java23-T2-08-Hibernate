package ru.teamscore.java23.hbooking.service;

import jakarta.persistence.EntityExistsException;
import ru.teamscore.java23.hbooking.entity.Booking;
import ru.teamscore.java23.hbooking.entity.Passenger;
import ru.teamscore.java23.hbooking.entity.Ticket;
import ru.teamscore.java23.hbooking.entity.TicketFlight;
import ru.teamscore.java23.hbooking.repository.BookingRepository;
import ru.teamscore.java23.hbooking.repository.TicketRepository;
import ru.teamscore.java23.hbooking.service.dto.BookingResult;
import ru.teamscore.java23.hbooking.service.dto.FlightReservation;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

public class BookingManager {
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;

    public BookingManager(BookingRepository bookingRepository, TicketRepository ticketRepository) {
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
    }

    public BookingResult book(List<Passenger> passengers,
                              List<FlightReservation> reservations) {
        Booking booking;
        while (true) {
            int i = 1;
            try {
                booking = new Booking();
                booking.setBookDate(ZonedDateTime.now());
                booking.setBookRef(getNextBookingRef());
                for (Passenger passenger : passengers) {
                    Ticket ticket = new Ticket();
                    booking.getTickets().add(ticket);
                    ticket.setBooking(booking);
                    ticket.setPassenger(passenger);
                    ticket.setTicketNo(getNextTicketNo(i++));
                    for (FlightReservation reservation : reservations) {
                        TicketFlight flight = new TicketFlight(ticket.getTicketNo(), reservation.flightId());
                        ticket.getFlights().add(flight);
                        flight.setFareConditions(reservation.fareConditions());
                        flight.setAmount(reservation.amount());
                    }
                }
                bookingRepository.save(booking);
                break;
            } catch (EntityExistsException e) {
                continue;
            } catch (Exception e) {
                return new BookingResult("ERROR: " + e.getMessage(),
                        null, null);
            }
        }
        if (booking == null) {
            return new BookingResult("UNKNOWN ERROR",
                    null, null);
        }
        return new BookingResult("SUCCESS", booking.getBookRef(), booking.getTotalAmount());
    }

    private Random rnd = new Random();
    private String getNextBookingRef() {
        //return String.format("TT%04d", rnd.nextInt(1, 10000));
        String lastRef = bookingRepository.getLastBookingRef();
        int lastNumber = (lastRef == null) ? 1 : Integer.parseInt(lastRef.substring(2));
        return String.format("TT%04d", lastNumber + 1);
    }

    private String getNextTicketNo(int i) {
        //return String.format("TEST%09d", rnd.nextInt(1, 10000));
        String lastNo = ticketRepository.getLastTicketNo();
        int lastNumber = (lastNo == null) ? i : Integer.parseInt(lastNo.substring(4));
        return String.format("TEST%09d", lastNumber + i);
    }
}
