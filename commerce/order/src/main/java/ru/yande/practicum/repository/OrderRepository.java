package ru.yande.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yande.practicum.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByUsername(String username);
}
