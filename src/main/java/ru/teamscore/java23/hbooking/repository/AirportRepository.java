package ru.teamscore.java23.hbooking.repository;

import jakarta.persistence.EntityManager;
import ru.teamscore.java23.hbooking.entity.Airport;

public class AirportRepository {
    EntityManager entityManager;

    public AirportRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Airport findByCode(String code) {
        return entityManager.find(Airport.class, code);
    }
}
