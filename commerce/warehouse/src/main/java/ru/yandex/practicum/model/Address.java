package ru.yandex.practicum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
public class Address {
    @Id
    private UUID id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private String flat;

    public Address() {
        this.id = UUID.randomUUID();
    }

    public Address(String country, String city, String street, String house, String flat) {
        this.id = UUID.randomUUID();
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }
}
