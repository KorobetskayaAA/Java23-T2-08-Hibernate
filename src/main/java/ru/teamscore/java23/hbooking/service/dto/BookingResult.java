package ru.teamscore.java23.hbooking.service.dto;

import java.math.BigDecimal;

public record BookingResult(String status, String bookingRef, BigDecimal totalAmount) {}
