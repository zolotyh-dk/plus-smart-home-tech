package ru.yandex.practicum.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.ProductCategory;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.dto.SetProductQuantityStateRequest;

import java.util.List;
import java.util.UUID;

public interface ShoppingStoreOperations {
    @GetMapping("/{productId}")
    ProductDto getProductById(@PathVariable UUID productId);

    @GetMapping
    List<ProductDto> getProducts(@RequestParam ProductCategory category, Pageable pageable);

    @PostMapping
    ProductDto addProduct(@RequestBody @Valid ProductDto productDto);

    @PutMapping
    ProductDto updateProduct(@RequestBody @Valid ProductDto productDto);

    @PutMapping("/quantityState")
    boolean updateQuantityState(@RequestBody @Valid SetProductQuantityStateRequest setProductQuantityStateRequest);

    @PutMapping("/removeProductFromStore")
    boolean removeProduct(@RequestParam @NotNull UUID productId);
}
