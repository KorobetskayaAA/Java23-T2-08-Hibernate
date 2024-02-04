package ru.teamscore.java23.hbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Map;

@Embeddable
public class Passenger {
    @Column(name = "passenger_id", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String passengerId;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "contact_data", columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> contactData;

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Map<String, String> getContactData() {
        return contactData;
    }

    public void setContactData(Map<String, String> contactData) {
        this.contactData = contactData;
    }
}
