package com.yhao.fileStorage.service.impl;

import com.yhao.fileStorage.dao.UserFileDao;
import com.yhao.fileStorage.entity.UserFile;
import com.yhao.fileStorage.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    UserFileDao userFileDao;

    @Override
    public List<UserFile> queryByUserId(Integer id, Integer page, Integer limit) {
        int begin = (page-1) * limit;
        int offset = limit;
        return userFileDao.queryByUserId(id, begin, offset);
    }

    @Override
    public void save(UserFile userFile) {
        userFile.setDownloadCounts(0).setUploadTime(new Date());
        userFileDao.save(userFile);
    }

    @Override
    public UserFile queryByUserFileId(Integer id) {
        return userFileDao.queryByUserFileId(id);
    }

    @Override
    public void update(UserFile userFile) {
        userFileDao.update(userFile);
    }

    @Override
    public void delete(Integer id) {
        userFileDao.delete(id);
    }

    @Override
    public int queryFileCounts(Integer id) {
        return userFileDao.queryFileCounts(id);
    }


}
