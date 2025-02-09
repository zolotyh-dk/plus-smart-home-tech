package ru.yandex.practicum.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
public class WarehouseProductId {
    private UUID productId;
    private UUID warehouseId;
}
