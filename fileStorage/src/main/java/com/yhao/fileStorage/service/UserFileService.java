package com.yhao.fileStorage.service;

import com.yhao.fileStorage.entity.UserFile;

import java.util.List;

public interface UserFileService {
    List<UserFile> queryByUserId(Integer id, Integer page, Integer limit);

    void save(UserFile userFile);

    UserFile queryByUserFileId(Integer id);

    void update(UserFile userFile);

    void delete(Integer id);

    int queryFileCounts(Integer id);
}
