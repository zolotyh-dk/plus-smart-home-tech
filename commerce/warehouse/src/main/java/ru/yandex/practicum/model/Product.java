package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "products")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
public class Product {
    @Id
    private UUID id;

    @Column(name = "fragile")
    private boolean fragile;

    @Embedded
    private Dimensions dimensions;

    @Column(name = "weight")
    private double weight;
}
