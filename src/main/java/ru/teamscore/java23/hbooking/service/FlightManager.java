package ru.teamscore.java23.hbooking.service;

import ru.teamscore.java23.hbooking.entity.Flight;
import ru.teamscore.java23.hbooking.repository.FlightRepository;
import ru.teamscore.java23.hbooking.service.dto.FlightInfo;
import ru.teamscore.java23.hbooking.service.dto.FlightShedule;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class FlightManager {
    private final FlightRepository flightRepository;

    public FlightManager(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public long getFlightsCount() {
        return flightRepository.getAllCount();
    }

    public List<FlightInfo> getAllFlights(int pageNumber, int pageSize) {
        return flightRepository.getAll(pageNumber, pageSize).stream()
                .map(f -> new FlightInfo(f))
                .toList();
    }

    public FlightShedule getFlightShedule(String flightNumber) {
        return new FlightShedule(flightNumber, flightRepository.findByNo(flightNumber));
    }

    public Duration departure(int flightId) {
        Flight flight = flightRepository.findById(flightId);
        if (flight.getActualDeparture() == null) {
            flight.setActualDeparture(ZonedDateTime.now());
            flightRepository.save(flight);
        }
        return Duration.between(flight.getScheduledDeparture(), flight.getActualDeparture());
    }
}
