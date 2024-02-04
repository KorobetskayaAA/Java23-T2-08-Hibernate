package ru.teamscore.java23.hbooking.repository;

import jakarta.persistence.EntityManager;
import ru.teamscore.java23.hbooking.entity.Flight;

import java.util.List;

public class FlightRepository {
    EntityManager entityManager;

    public FlightRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Flight findById(int id) {
        return entityManager.find(Flight.class, id);
    }

    public Flight save(Flight flight) {
        entityManager.getTransaction().begin();
        entityManager.persist(flight);
        entityManager.getTransaction().commit();
        return flight;
    }

    public Flight update(Flight flight) {
        entityManager.getTransaction().begin();
        entityManager.merge(flight);
        entityManager.getTransaction().commit();
        return flight;
    }


    public Flight delete(int id) {
        Flight flight = findById(id);
        if (flight != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(flight);
            entityManager.getTransaction().commit();
        }
        return flight;
    }

    public long getAllCount() {
        return entityManager
                .createQuery("select count(*) from Flight", Long.class)
                .getSingleResult();
    }

    public List<Flight> getAll(int pageNumber, int pageSize) {
        return entityManager
                .createQuery("from Flight order by id", Flight.class)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }
    public List<Flight> findByNo(String no) {
        return entityManager
                .createQuery("from Flight where flightNo = :no", Flight.class)
                .setParameter("no", no)
                .getResultList();
    }
}
