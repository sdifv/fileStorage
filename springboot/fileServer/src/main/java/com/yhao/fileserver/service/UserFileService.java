package com.yhao.fileserver.service;

import com.yhao.fileserver.entity.UserFile;

import java.util.List;

public interface UserFileService {
    List<UserFile> queryByUserId(Integer id);
}
