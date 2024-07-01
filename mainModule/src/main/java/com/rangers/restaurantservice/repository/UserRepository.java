package com.rangers.restaurantservice.repository;

import com.rangers.restaurantservice.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User getUserByChatId(String chatId);
}
