package ru.teamscore.java23.hbooking.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.teamscore.java23.hbooking.entity.Ticket;

public class TicketRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public TicketRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public void persist(Ticket ticket) {
        entityManager.persist(ticket);
    }

    public String getLastTicketNo() {
        CriteriaQuery<String> ticketNoQuery = criteriaBuilder.createQuery(String.class);
        Root<Ticket> root = ticketNoQuery.from(Ticket.class);
        ticketNoQuery
                .where(criteriaBuilder.like(root.get("ticketNo"), "TEST%"))
                .orderBy(criteriaBuilder.desc(root.get("ticketNo")))
                .select(root.get("ticketNo"));
        try {
            return entityManager.createQuery(ticketNoQuery)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
