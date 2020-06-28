package com.yhao.fileserver.dao;

import com.yhao.fileserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    public User login(User user);
}
