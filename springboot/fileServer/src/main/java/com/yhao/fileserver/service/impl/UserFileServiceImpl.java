package com.yhao.fileserver.service.impl;

import com.yhao.fileserver.dao.UserFileDao;
import com.yhao.fileserver.entity.UserFile;
import com.yhao.fileserver.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    UserFileDao userFileDao;

    @Override
    public List<UserFile> queryByUserId(Integer id) {
        return userFileDao.queryByUserId(id);
    }
}
