package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shopping_cart")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString(exclude = "products")
public class ShoppingCart {
    @Id
    private UUID id;

    @OneToMany(mappedBy = "shoppingCart",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ShoppingCartProduct> products = new ArrayList<>();

    @PrePersist
    protected void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
