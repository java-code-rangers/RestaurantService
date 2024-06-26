package com.rangers.restaurantservice.interf;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandlerService {
    Object handleUpdate(Update update);
}
