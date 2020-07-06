package com.yhao.fileStorage.service.impl;

import com.yhao.fileStorage.dao.UserDao;
import com.yhao.fileStorage.entity.User;
import com.yhao.fileStorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public void register(User user) {
        System.out.println(user);
        userDao.register(user);
    }


}
