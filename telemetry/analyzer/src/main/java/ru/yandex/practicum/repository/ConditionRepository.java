package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.model.ScenarioCondition;

@Repository
public interface ConditionRepository extends JpaRepository<ScenarioCondition, Long> {
}