package ru.yandex.practicum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shopping_cart")
@EqualsAndHashCode(of = "id")
public class ShoppingCart {
    @Id
    private UUID id;

    @OneToMany
    private List<ShoppingCartProduct> products;
}
