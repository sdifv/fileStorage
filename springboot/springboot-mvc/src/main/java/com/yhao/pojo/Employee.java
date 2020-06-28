package com.yhao.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 21:14 2020/4/5
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Data
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;// 0 男 1女
    private Department department;
    private Date birth;

    public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        //创建默认的日期
        this.birth = new Date();
    }
}
