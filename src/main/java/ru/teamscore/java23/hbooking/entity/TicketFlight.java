package ru.teamscore.java23.hbooking.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Entity
@Table(name = "ticket_flights", schema = "bookings")
public class TicketFlight {
    @Embeddable
    public static class TicketFlightPK {
        @Column(name = "ticket_no")
        private String ticketNo;
        @Column(name = "flight_id")
        private int flightId;
    }

    @EmbeddedId
    private TicketFlightPK pk = new TicketFlightPK();

    @ManyToOne
    @JoinColumn(name = "ticket_no", insertable = false, updatable = false)
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(name = "flight_id", insertable = false, updatable = false)
    private Flight flight;

    @Column(name = "fare_conditions", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private FareConditions fareConditions;

    @Column(nullable = false, precision = 10, scale = 2)
    @JdbcTypeCode(SqlTypes.NUMERIC)
    private BigDecimal amount;

    public Ticket getTicket() {
        return ticket;
    }

    public TicketFlight() {
    }

    public TicketFlight(String ticketNo, int flightId) {
        this.pk.ticketNo = ticketNo;
        this.pk.flightId = flightId;
    }

    public FareConditions getFareConditions() {
        return fareConditions;
    }

    public void setFareConditions(FareConditions fareConditions) {
        this.fareConditions = fareConditions;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
