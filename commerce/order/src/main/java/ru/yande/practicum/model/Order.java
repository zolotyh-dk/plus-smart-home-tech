package ru.yande.practicum.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.dto.order.OrderState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "order", name = "orders")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Order {
    @Id
    private UUID id;

    @Column(name = "shopping_cart_id")
    private UUID shoppingCartId;

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderProduct> products;

    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "delivery_id")
    private UUID deliveryId;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(name = "delivery_weight")
    private Double deliveryWeight;

    @Column(name = "delivery_volume")
    private Double deliveryVolume;

    @Column(name = "fragile")
    private Boolean fragile;

    @Column(name = "totlal_price")
    private Double totalPrice;

    @Column(name = "delivery_price")
    private Double deliveryPrice;

    @Column(name = "product_price")
    private Double productPrice;

    public Order() {
        id = UUID.randomUUID();
        products = new ArrayList<>();
    }
}
