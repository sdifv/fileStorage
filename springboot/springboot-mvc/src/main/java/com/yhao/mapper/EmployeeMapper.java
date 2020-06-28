package com.yhao.mapper;

import com.yhao.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {

    public List<Employee> queryEmployeeList();

    public Employee queryEmployeeById(int id);

    public void addEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(int id);
}
