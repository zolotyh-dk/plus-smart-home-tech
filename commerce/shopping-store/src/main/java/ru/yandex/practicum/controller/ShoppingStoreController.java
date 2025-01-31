package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.dto.product.ProductCategory;
import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.dto.product.SetProductQuantityStateRequest;
import ru.yandex.practicum.service.ShoppingStoreFacade;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/shopping-store")
@RequiredArgsConstructor
public class ShoppingStoreController implements ShoppingStoreOperations {
    private final ShoppingStoreFacade facade;

    @Override
    public ProductDto getProductById(UUID productId) {
        log.info("GET /api/v1/shopping-store/{} - Получение товара", productId);
        ProductDto response = facade.getProductById(productId);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public List<ProductDto> getProducts(ProductCategory category, Pageable pageable) {
        log.info("GET /api/v1/shopping-store - Получение списка товаров: category={}, page={}, size={}, sort={}",
                category, pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        List<ProductDto> response = facade.getProducts(category, pageable);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        log.info("POST /api/v1/shopping-store - Добавление товара: {}", productDto);
        ProductDto response = facade.addProduct(productDto);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        log.info("PUT /api/v1/shopping-store - Обновление товара: {}", productDto);
        ProductDto response = facade.updateProduct(productDto);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public boolean updateQuantityState(SetProductQuantityStateRequest request) {
        log.info("PUT /api/v1/shopping-store/quantityState - Обновление количества: {}", request);
        boolean response = facade.updateQuantityState(request);
        log.info("Ответ: {}", response);
        return response;
    }

    @Override
    public boolean removeProduct(UUID productId) {
        log.info("PUT /api/v1/shopping-store/removeProductFromStore - Удаление товара: {}", productId);
        boolean response = facade.removeProduct(productId);
        log.info("Ответ: {}", response);
        return response;
    }
}
