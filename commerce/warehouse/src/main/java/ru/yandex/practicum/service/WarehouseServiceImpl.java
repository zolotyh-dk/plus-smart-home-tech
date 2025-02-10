package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.dto.cart.BookedProductsDto;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddProductToWarehouseRequest;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.dto.warehouse.NewProductInWarehouseRequest;
import ru.yandex.practicum.exception.NoSpecifiedProductInWarehouseException;
import ru.yandex.practicum.exception.ProductInShoppingCartLowQuantityInWarehouse;
import ru.yandex.practicum.mapper.AddressMapper;
import ru.yandex.practicum.mapper.ProductMapper;
import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.repository.ProductRepository;

import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {
    private static final String[] ADDRESSES = new String[] {"ADDRESS_1", "ADDRESS_2"};
    private final ProductMapper productMapper;
    private final AddressMapper addressMapper;
    private final ProductRepository productRepository;

    @Override
    public void addProduct(NewProductInWarehouseRequest request) {
        log.debug("Добавляем продукт на склад: {}", request);
        Product product = productMapper.toProduct(request);
        Product savedProduct = productRepository.save(product);
        log.debug("Добавили продукт на склад: {}", savedProduct);
    }

    @Transactional(readOnly = true)
    @Override
    public BookedProductsDto check(ShoppingCartDto shoppingCartDto) {
        log.debug("Проверяем доступность товаров из корзины: {}", shoppingCartDto);
        Map<UUID, Long> requiredProducts = shoppingCartDto.products();
        Set<Product> storedProducts = productRepository.findAllByIdIn(requiredProducts.keySet());
        Set<UUID> deficitProducts = new HashSet<>();
        storedProducts.forEach(product -> {
            long requiredQuantity = requiredProducts.get(product.getId());
            long existingQuantity = product.getQuantity();
                    if (existingQuantity < requiredQuantity) {
                        log.debug("Товара с ID: {} недостаточно на складе. Требуется: {}, есть: {}",
                                product.getId(), requiredQuantity, existingQuantity);
                        deficitProducts.add(product.getId());
                    }
                });
        if (!deficitProducts.isEmpty()) {
            throw new ProductInShoppingCartLowQuantityInWarehouse(deficitProducts);
        }
        BookedProductsDto bookedProductsDto = productMapper.toDto(requiredProducts, storedProducts);
        log.debug("Сформировали доставку: {}", bookedProductsDto);
        return bookedProductsDto;
    }

    @Override
    public void increaseQuantity(AddProductToWarehouseRequest request) {
        log.debug("Увеличиваем количество товара с ID: {} на {}", request.productId(), request.quantity());
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new NoSpecifiedProductInWarehouseException(request.productId()));
        product.setQuantity(product.getQuantity() + request.quantity());
        log.debug("Увеличили количество товара: {}", product);
    }

    @Override
    public AddressDto getAddress() {
        log.debug("Определяем адрес склада.");
        String address = ADDRESSES[Random.from(new SecureRandom()).nextInt(ADDRESSES.length)];
        AddressDto addressDto = addressMapper.toDto(address);
        log.debug("Адрес склада: {}", addressDto);
        return addressDto;
    }
}
