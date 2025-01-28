package ru.yandex.practicum.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Товар, продаваемый в интернет-магазине
 *
 * @param productId       идентификатор товара в БД
 * @param productName     наименование товара
 * @param description     описание товара
 * @param imageSrc        ссылка на картинку во внешнем хранилище или SVG
 * @param quantityState   статус, перечисляющий состояние остатка как свойства товара
 * @param productState    статус товара
 * @param productCategory категория товара
 * @param price           цена товара
 */
public record ProductDto(
        @NotNull
        UUID productId,

        @NotNull
        String productName,

        @NotNull
        String description,

        String imageSrc,

        @NotNull
        QuantityState quantityState,

        @NotNull
        ProductState productState,

        ProductCategory productCategory,

        @NotNull
        @Min(1)
        Double price
) {
}
