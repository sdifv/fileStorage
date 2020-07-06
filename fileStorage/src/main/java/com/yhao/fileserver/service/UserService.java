package com.yhao.fileserver.service;

import com.yhao.fileserver.entity.User;

public interface UserService {
    User login(User user);

    void register(User user);
}
