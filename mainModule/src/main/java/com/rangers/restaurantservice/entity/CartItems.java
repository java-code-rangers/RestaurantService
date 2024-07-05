package com.rangers.restaurantservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@RequiredArgsConstructor
@Document(collection = "cart_items")
public class CartItems {
    @Id
    private ObjectId cartItemsId;
    private Product product;
    private User user;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItems cartItems = (CartItems) o;
        return Objects.equals(cartItemsId, cartItems.cartItemsId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cartItemsId);
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "cartItemsId=" + cartItemsId +
                ", product=" + product +
                ", userId=" + user.getUserId() +
                ", quantity=" + quantity +
                '}';
    }
}
