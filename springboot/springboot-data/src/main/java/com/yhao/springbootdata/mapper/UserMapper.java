package com.yhao.springbootdata.mapper;

import com.yhao.springbootdata.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// 表示这是一个 mybatis 的 mapper 类
@Mapper
// dao 数据访问层的类需要使用@Component或者@Repository注解
@Repository
public interface UserMapper {

    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
