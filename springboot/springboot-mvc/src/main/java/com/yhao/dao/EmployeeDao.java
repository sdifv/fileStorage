package com.yhao.dao;

import com.yhao.pojo.Department;
import com.yhao.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 20:47 2020/4/6
 * @ Description：员工类
 * @ Modified By：
 * @Version: $
 */
//员工dao
    @Repository
public class EmployeeDao {
    //模拟数据库的信息
    private static Map<Integer, Employee> employees=null;
    //员工所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees=new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001,"AA1","1918308849@qq.com",0,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"AA2","1918308849@qq.com",1,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"AA3","1918308849@qq.com",0,new Department(103,"教研部")));
        employees.put(1004,new Employee(1004,"AA4","1918308849@qq.com",1,new Department(104,"运营部")));
        employees.put(1005,new Employee(1005,"AA5","1918308849@qq.com",0,new Department(105,"后勤部")));

    }
    //主键自增
    private static Integer initId=1006;
    //增加一个员工
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }
    //查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }
    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return  employees.get(id);
    }
    //删除员工通过id
    public void delete(Integer id){
        employees.remove(id);
    }
}
