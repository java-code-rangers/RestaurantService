package com.rangers.restaurantservice.service.impl;

import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.dto.OrderDetailsDto;
import com.rangers.restaurantservice.dto.OrderDto;
import com.rangers.restaurantservice.entity.Order;
import com.rangers.restaurantservice.entity.OrderDetails;
import com.rangers.restaurantservice.entity.Product;
import com.rangers.restaurantservice.enums.Status;
import com.rangers.restaurantservice.mapper.OrderDetailsMapper;
import com.rangers.restaurantservice.mapper.OrderMapper;
import com.rangers.restaurantservice.repository.*;
import com.rangers.restaurantservice.service.OrderService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderDetailsMapper orderDetailsMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    @Transactional
    public OrderDto findById(ObjectId id) throws ObjectNotFoundException {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Order with id '%s' not found", id)));
    }

    @Override
    @Transactional
    public List<OrderDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    @Transactional
    public List<OrderDto> findAllByUserId(ObjectId userId) {
        return mapper.toDtoList(repository.findAllByUser_UserId(userId));
    }

    @Override
    @Transactional
    public List<OrderDto> findAllByCategory(ObjectId categoryId) throws ObjectNotFoundException {
        List<ObjectId> productIds = productRepository.getProductsByCategory(
                        categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new ObjectNotFoundException(String.format("Category with id '%s' not found", categoryId))))
                .stream()
                .map(Product::getProductId)
                .toList();

        List<ObjectId> orderIds = new ArrayList<>();

        for (ObjectId productId : productIds) {
            orderIds = orderDetailsRepository.findAllByProductId(productId).stream()
                    .map(orderDetail -> orderDetail.getOrder().getOrderId())
                    .toList();
        }
        return repository.findAllById(orderIds).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<OrderDto> findOrderByStatus(Status status) {
        return mapper.toDtoList(repository.findAllByStatus(status));
    }

    @Override
    @Transactional
    public void update(ObjectId id, Status status) throws ObjectNotFoundException {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Order with id '%s' not found", id)));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        repository.save(order);
    }

    @Override
    @Transactional
    public OrderDto create(List<CartItemsDto> cartItemsDtos) throws ObjectNotFoundException {
        Order order = new Order();
        Order finalOrder = repository.save(order);

        List<OrderDetailsDto> orderDetailsDtos = createOrderDetails(cartItemsDtos);
        orderDetailsDtos.forEach(orderDetailsDto -> orderDetailsDto.setOrderId(finalOrder.getOrderId()));

        finalOrder.setSum(orderDetailsDtos.stream()
                .map(orderDetailsDto -> {
                    Product product;
                    try {
                        product = productRepository.findById(orderDetailsDto.getProductId())
                                .orElseThrow(() -> new ObjectNotFoundException(String.format("Product with id '%s' not found", orderDetailsDto.getProductId())));
                    } catch (ObjectNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return product.getPrice().multiply(BigDecimal.valueOf(orderDetailsDto.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add));


        List<OrderDetails> orderDetails = orderDetailsMapper.toEntityList(orderDetailsDtos, repository);
        orderDetailsRepository.saveAll(orderDetails);

        finalOrder.setOrderDetailsList(orderDetails);
        finalOrder.setUser(userRepository.findById(cartItemsDtos.getFirst().getUserId())
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with id '%s' not found", cartItemsDtos.getFirst().getUserId()))));

        finalOrder.setCreatedAt(LocalDateTime.now());
        finalOrder.setOrderDate(LocalDateTime.now());
        finalOrder.setUpdatedAt(LocalDateTime.now());
        finalOrder.setStatus(Status.PROCESSING);

        return mapper.toDto(repository.save(finalOrder));
    }


    @Transactional
    public List<OrderDetailsDto> createOrderDetails(List<CartItemsDto> cartItemsDtos) {
        return cartItemsDtos.stream()
                .map(orderDetailsMapper::cartItemsToOrderDetailsDto)
                .toList();
    }
}
