package com.rangers.restaurantservice.service.impl;

import com.rangers.restaurantservice.dto.RequestReviewDto;
import com.rangers.restaurantservice.dto.ResponseReviewDto;
import com.rangers.restaurantservice.entity.Order;
import com.rangers.restaurantservice.entity.Review;
import com.rangers.restaurantservice.entity.User;
import com.rangers.restaurantservice.mapper.ReviewMapper;
import com.rangers.restaurantservice.repository.ReviewRepository;
import com.rangers.restaurantservice.repository.UserRepository;
import com.rangers.restaurantservice.service.ReviewService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public String createReview(RequestReviewDto requestReviewDto) {
        Review review = reviewMapper.toEntity(requestReviewDto);
        Order order = new Order();
        order.setOrderId(new ObjectId("6681797250f67e6871d91511"));
        User user = userRepository.getUserByUserId(requestReviewDto.getUserId()) ;
        review.setUser(user);
        review.setOrder(order);
        reviewRepository.save(review);
        return "Review created";
    }

    @Override
    public List<ResponseReviewDto> getReviews() throws ObjectNotFoundException {
        return List.of();
    }

    @Override
    public ResponseReviewDto getReview(ObjectId orderId) throws ObjectNotFoundException {
        return null;
    }
}
