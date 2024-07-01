package com.rangers.restaurantservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "order_details")
public class OrderDetails {
    @Id
    private UUID odId;
    private Integer quantity;
    private UUID productId;
    private Order oder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(odId, that.odId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(odId);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "odId=" + odId +
                ", quantity=" + quantity +
                ", productId=" + productId +
                ", oderId=" + oder.getOrderId() +
                '}';
    }
}
