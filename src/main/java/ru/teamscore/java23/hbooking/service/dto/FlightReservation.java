package ru.teamscore.java23.hbooking.service.dto;

import ru.teamscore.java23.hbooking.entity.FareConditions;

import java.math.BigDecimal;

public record FlightReservation(int flightId, FareConditions fareConditions, BigDecimal amount) {
}
