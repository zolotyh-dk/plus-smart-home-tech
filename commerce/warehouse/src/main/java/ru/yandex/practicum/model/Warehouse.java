package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "warehouses")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
public class Warehouse {
    private static final String[] ADDRESSES = new String[] {"ADDRESS_1", "ADDRESS_2"};

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<WarehouseProduct> products = new ArrayList<>();

    public Warehouse() {
        String randomAddress = ADDRESSES[Random.from(new SecureRandom()).nextInt(0, 1)];
        this.address = new Address(randomAddress, randomAddress, randomAddress, randomAddress, randomAddress);
        this.id = UUID.randomUUID();
    }
}
