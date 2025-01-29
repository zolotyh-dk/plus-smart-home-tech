package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.ProductCategory;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.dto.SetProductQuantityStateRequest;

import java.util.List;
import java.util.UUID;

public interface ShoppingStoreFacade {
    ProductDto getProductById(UUID productId);

    List<ProductDto> getProducts(ProductCategory category, int page, int size, List<String> sort);

    ProductDto addProduct(ProductDto productDto);


    ProductDto updateProduct(ProductDto productDto);

    boolean updateQuantityState(SetProductQuantityStateRequest request);

    boolean removeProduct(UUID productId);
}
