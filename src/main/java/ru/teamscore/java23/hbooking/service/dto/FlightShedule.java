package ru.teamscore.java23.hbooking.service.dto;

import ru.teamscore.java23.hbooking.entity.Flight;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightShedule {
    private String flightNo;
    private Airport departureAirport;
    private Airport arrivalAirport;

    private List<Shedule> sheduleList = new ArrayList<>();

    public record Airport(String code, String name, String city) {
        public Airport(ru.teamscore.java23.hbooking.entity.Airport airport) {
            this(airport.getCode(), airport.getName(), airport.getCity());
        }
        @Override
        public String toString() {
            return code() + "(" + city() + ")";
        }
    }
    public record Shedule(ZonedDateTime departure, ZonedDateTime arrival, String aircraft) {
        @Override
        public String toString() {
            return "Aircraft " + aircraft() + "\t" + departure + "\t" + arrival();
        }}

    public FlightShedule(
            String flightNo,
            List<Flight> flights
    ) {
        this.flightNo = flightNo;
        if (flights.size() == 0) {
            return;
        }
        this.departureAirport = new Airport(flights.get(0).getDepartureAirport());
        this.arrivalAirport = new Airport(flights.get(0).getArrivalAirport());
        for (var flight : flights) {
            if (flight.getDepartureAirport().getCode() != this.departureAirport.code() ||
                    flight.getArrivalAirport().getCode() != this.arrivalAirport.code() ) {
                throw new IllegalArgumentException("Расписание рейсов содержит разные аэропорты");
            }
            sheduleList.add(new Shedule(flight.getScheduledDeparture(),
                    flight.getScheduledArrival(),
                    flight.getAircraftCode()));
        }
    }

    @Override
    public String toString() {
        return "Flight " + flightNo +
                " shedule from " + departureAirport +
                " to " + arrivalAirport + "\n" +
                String.join("\n", sheduleList.stream().map(s -> s.toString()).toList());
    }
}
