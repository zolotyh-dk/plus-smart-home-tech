package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "warehouse_product")
@Getter
@Setter
@ToString(of = "id")
public class WarehouseProduct {
    @EmbeddedId
    private WarehouseProductId id = new WarehouseProductId();

    @ManyToOne
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private long quantity;
}
