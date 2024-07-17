package com.rangers.restaurantservice.apprunner.util;

import com.rangers.restaurantservice.dto.ProductDto;
import lombok.experimental.UtilityClass;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@UtilityClass
public class DataCreator {
    public static ProductDto getProductDtoOne() {
        return ProductDto.builder()
                .productId(new ObjectId("66842a43c995c63518773a6f"))
                .name("Торт ванильный")
                .description("сладкий, вкусный торт")
                .price(BigDecimal.valueOf(15))
                .categoryId(new ObjectId("6685d4d6d204885cfc728065"))
                .userId(new ObjectId("6681797250f67e6871d91579"))
                .imageLink(null)
                .isActive(true)
                .build();
    }

    public static ProductDto getProductDtoTwo() {
        return ProductDto.builder()
                .name("Торт ежик")
                .description("с каплями шоколада сверху")
                .price(BigDecimal.valueOf(12))
                .categoryId(new ObjectId("6685d4d6d204885cfc728065"))
                .userId(new ObjectId("6681797250f67e6871d91579"))
                .imageLink(null)
                .isActive(true)
                .build();
    }

    public static ProductDto getProductDtoThree() {
        return ProductDto.builder()
                .productId(new ObjectId("66842a43c995c63518773a6f"))
                .name("Cake new")
                .description("new")
                .price(BigDecimal.valueOf(10))
                .categoryId(new ObjectId("6685d4d6d204885cfc728065"))
                .userId(new ObjectId("6681797250f67e6871d91579"))
                .imageLink(null)
                .isActive(false)
                .build();
    }
}
