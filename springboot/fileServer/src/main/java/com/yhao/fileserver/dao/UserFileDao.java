package com.yhao.fileserver.dao;

import com.yhao.fileserver.entity.UserFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.annotation.ManagedBean;
import java.util.List;

@Mapper
@Repository
public interface UserFileDao {
    // 根据userId查询获取用户的文件列表
    List<UserFile> queryByUserId(Integer id);
}
