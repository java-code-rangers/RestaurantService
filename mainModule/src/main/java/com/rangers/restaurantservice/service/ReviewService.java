package com.rangers.restaurantservice.service;

import com.rangers.restaurantservice.dto.RequestReviewDto;
import com.rangers.restaurantservice.dto.ResponseReviewDto;
import javassist.tools.rmi.ObjectNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;

public interface ReviewService {
    String createReview(RequestReviewDto requestReviewDto);
    List<ResponseReviewDto> getReviews() throws ObjectNotFoundException;
    ResponseReviewDto getReview(ObjectId orderId) throws ObjectNotFoundException;
}
