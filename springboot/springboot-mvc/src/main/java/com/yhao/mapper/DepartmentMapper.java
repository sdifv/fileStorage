package com.yhao.mapper;

import com.yhao.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    public List<Department> queryDepartments();

    public Department queryDepartmentById(int id);

}
