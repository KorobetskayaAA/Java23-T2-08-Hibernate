package ru.teamscore.java23.hbooking.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "flights", schema = "bookings")
public class Flight {
    @Id
    @Column(name = "flight_id", nullable = false)
    private int id;
    @Column(name = "flight_no")
    private String flightNo;
    @Column(name = "scheduled_departure")
    private ZonedDateTime scheduledDeparture;
    @Column(name = "scheduled_arrival")
    private ZonedDateTime scheduledArrival;
    @Column(name = "departure_airport")
    private String departureAirportCode;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_airport", insertable = false, updatable = false)
    private Airport departureAirport;
    @Column(name = "arrival_airport")
    private String arrivalAirportCode;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_airport", insertable = false, updatable = false)
    private Airport arrivalAirport;
    private String status;
    @Column(name = "aircraft_code")
    private String aircraftCode;
    @Column(name = "actual_departure")
    private ZonedDateTime actualDeparture;
    @Column(name = "actual_arrival")
    private ZonedDateTime actualArrival;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public ZonedDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(ZonedDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public ZonedDateTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(ZonedDateTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public ZonedDateTime getActualDeparture() {
        return actualDeparture;
    }

    public void setActualDeparture(ZonedDateTime actualDeparture) {
        this.actualDeparture = actualDeparture;
    }

    public ZonedDateTime getActualArrival() {
        return actualArrival;
    }

    public void setActualArrival(ZonedDateTime actualArrival) {
        this.actualArrival = actualArrival;
    }
}