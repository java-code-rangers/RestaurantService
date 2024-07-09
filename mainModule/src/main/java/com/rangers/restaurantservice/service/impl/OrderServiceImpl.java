package com.rangers.restaurantservice.service.impl;

import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.dto.OrderDetailsDto;
import com.rangers.restaurantservice.dto.OrderDto;
import com.rangers.restaurantservice.entity.Category;
import com.rangers.restaurantservice.entity.Order;
import com.rangers.restaurantservice.entity.Product;
import com.rangers.restaurantservice.enums.Status;
import com.rangers.restaurantservice.mapper.OrderDetailsMapper;
import com.rangers.restaurantservice.mapper.OrderMapper;
import com.rangers.restaurantservice.repository.OrderDetailsRepository;
import com.rangers.restaurantservice.repository.OrderRepository;
import com.rangers.restaurantservice.repository.ProductRepository;
import com.rangers.restaurantservice.repository.UserRepository;
import com.rangers.restaurantservice.service.OrderService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderDetailsMapper orderDetailsMapper;
    private final ProductRepository productRepository;
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
        return mapper.toDtoList(repository.findAllByUserId(userId));
    }

    @Override
    @Transactional
    public List<OrderDto> findAllByCategory(Category category) {
        List<ObjectId> productIds = productRepository.getProductsByCategory(category).stream()
                .map(Product::getProductId)
                .toList();

        List<ObjectId> orderIds = orderDetailsRepository.findAllByProductIdIn(productIds).stream()
                .map(orderDetail -> orderDetail.getOrder().getOrderId())
                .toList();

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
        repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Order with id '%s' not found", id)))
                .setStatus(status);
    }

    @Override
    @Transactional
    public OrderDto create(List<CartItemsDto> cartItemsDtos) {
        Order order = new Order();
        List<OrderDetailsDto> orderDetailsDtos = createOrderDetails(cartItemsDtos);

        order.setOrderDetailsList(orderDetailsDtos.stream().map(orderDetailsMapper::toEntity).toList());
        orderDetailsDtos.stream()
                .map(orderDetailsDto -> {
                    try {
                        return productRepository.findById(orderDetailsDto.getProductId())
                                .orElseThrow(() -> new ObjectNotFoundException(String.format("Order with id '%s' not found", orderDetailsDto.getProductId())));
                    } catch (ObjectNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(product -> order.setSum(order.getSum().add(product.getPrice())));

        order.setUser(userRepository.findById(cartItemsDtos.getFirst().getUserId()).orElseThrow());
        return mapper.toDto(repository.save(order));
    }

    public List<OrderDetailsDto> createOrderDetails(List<CartItemsDto> cartItemsDtos) {
        return cartItemsDtos.stream().map(orderDetailsMapper::careItemsToOrderDetailsDto).toList();
    }
}
