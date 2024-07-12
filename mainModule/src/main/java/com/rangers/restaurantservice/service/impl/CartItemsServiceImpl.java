package com.rangers.restaurantservice.service.impl;

import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.entity.CartItems;
import com.rangers.restaurantservice.mapper.CartItemsMapper;
import com.rangers.restaurantservice.repository.CartItemsRepository;
import com.rangers.restaurantservice.repository.ProductRepository;
import com.rangers.restaurantservice.repository.UserRepository;
import com.rangers.restaurantservice.service.CartItemsService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemsServiceImpl implements CartItemsService {

    private final CartItemsRepository repository;
    private final CartItemsMapper mapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CartItemsDto findById(ObjectId id) throws ObjectNotFoundException {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Cart item with id '%s' not found", id)));
    }

    @Override
    @Transactional
    public List<CartItemsDto> findAll(ObjectId userId) {
        return mapper.toDtoList(repository.findAllByUserId(userId));
    }

    @Override
    @Transactional
    public CartItemsDto create(CartItemsDto cartItemsDto) throws ObjectNotFoundException {
        CartItems cartItems = mapper.toEntity(cartItemsDto);
        cartItems.setUser(userRepository.findById(cartItemsDto.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with id '%s' not found", cartItemsDto.getUserId()))));
        cartItems.setProduct(productRepository.findById(cartItemsDto.getProductId())
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Product with id '%s' not found", cartItemsDto.getProductId()))));
        return mapper.toDto(repository.save(cartItems));
    }

    @Override
    @Transactional
    public CartItemsDto updateQuantity(ObjectId id, Integer quantity) throws ObjectNotFoundException {
        CartItems cartItems = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Cart item with id '%s' not found", id)));
        cartItems.setQuantity(quantity);
        return mapper.toDto(repository.save(cartItems));
    }

    @Override
    @Transactional
    public void deleteAllByUserId(ObjectId userId) {
        repository.deleteAllByUserId(userId);
    }

    @Override
    @Transactional
    public void delete(ObjectId id) throws ObjectNotFoundException {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Cart item with id '%s' not found", id))));
    }
}
