package ru.teamscore.java23.hbooking.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.teamscore.java23.hbooking.entity.Booking;
import ru.teamscore.java23.hbooking.entity.Ticket;

public class BookingRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public BookingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Booking save(Booking booking) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(booking);
            transaction.commit();
            return booking;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public void persist(Booking booking) {
        entityManager.persist(booking);
    }
    public void merge(Booking booking) {
        entityManager.merge(booking);
    }

    public String getLastBookingRef() {
        CriteriaQuery<String> bookingRefQuery = criteriaBuilder.createQuery(String.class);
        Root<Booking> root = bookingRefQuery.from(Booking.class);
        bookingRefQuery
                .where(criteriaBuilder.like(root.get("bookRef"), "TT%"))
                .orderBy(criteriaBuilder.desc(root.get("bookRef")))
                .select(root.get("bookRef"));
        try {
            return entityManager.createQuery(bookingRefQuery)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
