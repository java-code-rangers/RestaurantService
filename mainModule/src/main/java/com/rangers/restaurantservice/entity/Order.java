package com.rangers.restaurantservice.entity;

import com.rangers.restaurantservice.enums.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private ObjectId orderId;
    private User user;
    private LocalDateTime orderDate;
    private Status status;
    private BigDecimal sum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderDetails> orderDetailsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + user.getUserId() +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", sum=" + sum +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }
}
