package ru.yandex.practicum.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.dto.cart.BookedProductsDto;
import ru.yandex.practicum.dto.cart.ChangeProductQuantityRequest;
import ru.yandex.practicum.dto.cart.ShoppingCartDto;
import ru.yandex.practicum.mapper.ShoppingCartMapper;
import ru.yandex.practicum.model.ShoppingCart;
import ru.yandex.practicum.repository.ShoppingCartRepository;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartDto getShoppingCart(String username) {
        log.debug("Запрашиваем корзину пользователя: {}", username);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUsername(username)
                .orElseGet(() -> {
                    log.debug("Корзина для пользователя: {} не найдена в DB. Создаем новую.", username);
                    ShoppingCart newShoppingCart = new ShoppingCart();
                    newShoppingCart.setUsername(username);
                    return newShoppingCart;
                });
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.toDto(shoppingCart);
        log.debug("Возвращаем корзину пользователя {}", shoppingCartDto);
        return shoppingCartDto;
    }

    @Override
    public ShoppingCartDto addProductsToCart(String username, Map<UUID, Long> products) {
        return null;
    }

    @Override
    public void deactivateShoppingCart(String username) {

    }

    @Override
    public ShoppingCartDto replaceShoppingCartContents(String username, Map<UUID, Long> products) {
        return null;
    }

    @Override
    public ShoppingCartDto changeProductQuantity(String username, ChangeProductQuantityRequest request) {
        return null;
    }

    @Override
    public BookedProductsDto bookProducts(String username) {
        return null;
    }
}
