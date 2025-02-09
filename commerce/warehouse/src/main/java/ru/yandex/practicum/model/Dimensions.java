package ru.yandex.practicum.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
public class Dimensions {
    private Double width;
    private Double height;
    private Double depth;
}
