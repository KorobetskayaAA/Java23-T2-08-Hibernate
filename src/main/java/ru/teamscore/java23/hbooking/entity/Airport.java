package ru.teamscore.java23.hbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "airports_data", schema = "bookings")
public class Airport {
    @Id
    @Column(name = "airport_code", length = 3)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String code;

    @Column(name = "airport_name", nullable = false, columnDefinition = "json")
    private String name;

    @Column(name = "city", nullable = false, columnDefinition = "json")
    private String city;

    @Column(columnDefinition = "point")
    private String coordinates;

    @Column(columnDefinition = "text")
    private String timezone;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
