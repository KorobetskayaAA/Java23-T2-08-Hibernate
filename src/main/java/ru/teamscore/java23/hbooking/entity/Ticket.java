package ru.teamscore.java23.hbooking.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tickets", schema = "bookings")
public class Ticket {
    @Id
    @Column(name = "ticket_no", nullable = false, length = 13)
    private String ticketNo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_ref")
    private Booking booking;

    @Embedded
    private Passenger passenger;

    @OneToMany(mappedBy = "ticket",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<TicketFlight> flights = new ArrayList<>();

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public List<TicketFlight> getFlights() {
        return flights;
    }
}