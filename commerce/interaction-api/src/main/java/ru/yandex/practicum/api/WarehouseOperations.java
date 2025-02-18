package ru.yandex.practicum.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.dto.cart.BookedProductsDto;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddProductToWarehouseRequest;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.dto.warehouse.NewProductInWarehouseRequest;

/**
 * API для обеспечения работы склада онлайн магазина
 */

public interface WarehouseOperations {
    /**
     * Добавить новый товар на склад.
     *
     * @param request Запрос на добавление нового товара на склад
     */
    @PostMapping
    void addProduct(@RequestBody @Valid NewProductInWarehouseRequest request);

    /**
     * Предварительно проверить что количество товаров на складе достаточно для данной корзины продуктов.
     *
     * @param shoppingCartDto Корзина товаров в онлайн магазине.
     * @return Общие сведения о зарезервированных товарах по корзине.
     */
    @PostMapping("/check")
    BookedProductsDto bookProducts(@RequestBody @Valid ShoppingCartDto shoppingCartDto);

    /**
     * Добавление определенного количества определенного товара на склад.
     *
     * @param request Запрос на увеличение единиц товара по его идентификатору
     */
    @PostMapping("/add")
    void increaseQuantity(@RequestBody @Valid AddProductToWarehouseRequest request);

    /**
     * Предоставить адрес склада для расчёта доставки.
     *
     * @return Актуальный адрес склада
     */
    @GetMapping("/address")
    AddressDto getAddress();
}
