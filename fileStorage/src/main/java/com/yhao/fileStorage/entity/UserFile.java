package com.yhao.fileStorage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class UserFile {
    private Integer id;
    private String fileName;
    private String ext;
    private String path;
    private long size;
    private String type;
    private Integer downloadCounts;
    private Date uploadTime;
    private Integer userId;
}
