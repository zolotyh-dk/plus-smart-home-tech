package ru.yandex.practicum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "shopping_cart_products")
public class ShoppingCartProduct {
    @Id
    private UUID id;

}
