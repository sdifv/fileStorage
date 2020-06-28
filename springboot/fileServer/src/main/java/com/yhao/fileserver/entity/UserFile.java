package com.yhao.fileserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserFile {
    private Integer id;
    private String fileName;
    private String ext;
    private String path;
    private String size;
    private String type;
    private Integer downloadCounts;
    private Date uploadTime;
    private Integer userId;
}
