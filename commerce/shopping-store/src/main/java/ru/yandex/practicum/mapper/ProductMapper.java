package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.dto.ProductDto;
import ru.yandex.practicum.model.Product;

@Component
public class ProductMapper {
    public Product toProduct(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(dto.productId());
        product.setName(dto.productName());
        product.setDescription(dto.description());
        product.setImageSrc(dto.imageSrc());
        product.setQuantityState(dto.quantityState());
        product.setCategory(dto.productCategory());
        product.setPrice(dto.price());
        return product;
    }

    public ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDto.builder()
                .productId(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .imageSrc(product.getImageSrc())
                .quantityState(product.getQuantityState())
                .productCategory(product.getCategory())
                .price(product.getPrice())
                .build();
    }
}
