package ru.teamscore.java23.hbooking.service.dto;


import ru.teamscore.java23.hbooking.entity.Flight;

import java.time.ZonedDateTime;

public class FlightInfo {
    private String flightNo;
    private Shedule departure;
    private Shedule arrival;
    private String status;
    private String aircraftCode;

    public record Shedule (
        ZonedDateTime scheduled,
        String city,
        ZonedDateTime actual
    ) {}

    private FlightInfo(String flightNo,
                      ZonedDateTime departureScheduled,
                      String departureCity,
                      ZonedDateTime departureActual,
                      ZonedDateTime arrivalScheduled,
                      String arrivalCity,
                      ZonedDateTime arrivalActual,
                      String status,
                      String aircraftCode) {
        this.flightNo = flightNo;
        this.departure = new Shedule(departureScheduled, departureCity, departureActual);
        this.arrival = new Shedule(arrivalScheduled, arrivalCity, arrivalActual);
        this.status = status;
        this.aircraftCode = aircraftCode;
    }

    public FlightInfo(Flight flight) {
         this(flight.getFlightNo(),
                 flight.getScheduledDeparture(),
                 flight.getDepartureAirport().getCity(),
                 flight.getActualDeparture(),
                 flight.getScheduledArrival(),
                 flight.getArrivalAirport().getCity(),
                 flight.getActualArrival(),
                 flight.getStatus(),
                 flight.getAircraftCode());
    }

    @Override
    public String toString() {
        return "Flight " + flightNo +
                " of aircraft " + aircraftCode +
                " " + status +
                " from " + departure.city() +
                " at " + departure.scheduled() +
                " (actual " + departure.actual() + ")" +
                " to " + arrival.city() +
                " at " + arrival.scheduled() +
                " (actual " + arrival.actual() + ")"
        ;
    }
}
