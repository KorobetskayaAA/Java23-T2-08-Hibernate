package ru.teamscore.java23.hbooking.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings", schema = "bookings")
public class Booking {
    @Id
    @Column(name = "book_ref", nullable = false, length = 6)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String bookRef;

    @Column(name = "book_date", nullable = false)
    @Basic
    private ZonedDateTime bookDate;

    @Column(name = "total_amount", nullable = false)
    @JdbcTypeCode(SqlTypes.NUMERIC)
    @Access(AccessType.PROPERTY)
    private BigDecimal totalAmount;

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return tickets.stream()
                    .flatMap(t -> t.getFlights().stream())
                    .map(tf -> tf.getAmount())
                    .reduce(BigDecimal.ZERO, (amount, sum) -> sum.add(amount).setScale(2));
    }

    @OneToMany(mappedBy = "booking", cascade = CascadeType.PERSIST)
    public List<Ticket> tickets = new ArrayList<>();

    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }
    public String getBookRef() {
        return bookRef;
    }
    public void setBookDate(ZonedDateTime bookDate) {
        this.bookDate = bookDate;
    }
    public ZonedDateTime getBookDate() {
        return bookDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
