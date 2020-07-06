package com.yhao.fileStorage.service;

import com.yhao.fileStorage.entity.User;

public interface UserService {
    User login(User user);

    void register(User user);
}
