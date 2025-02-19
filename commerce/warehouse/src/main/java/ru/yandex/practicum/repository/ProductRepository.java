package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.model.Product;

import java.util.Set;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Set<Product> findAllByIdIn(Set<UUID> uuids);
}
