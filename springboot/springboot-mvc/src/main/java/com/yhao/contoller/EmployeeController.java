package com.yhao.contoller;
import com.yhao.mapper.DepartmentMapper;
import com.yhao.mapper.EmployeeMapper;
import com.yhao.pojo.Department;
import com.yhao.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Author     ：Yan
 * @ Date       ：Created in 16:00 2020/4/9
 * @ Description：
 * @ Modified By：
 * @Version: $
 */

@Controller
public class EmployeeController {
//    @Autowired
//    EmployeeDao employeeDao;
//    @Autowired
//    DepartmentDao departmentDao;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    @RequestMapping("/emps")
    public String queryEmployeeList(Model model){
        List<Employee> employees = employeeMapper.queryEmployeeList();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddEmployee(Model model){
        //查询所有部门的信息
        List<Department> departments = departmentMapper.queryDepartments();
        System.out.println(departments);
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmployee(Employee employee){
        //添加操作
        System.out.println("save employee:"+employee);
        employeeMapper.addEmployee(employee);
        return "redirect:/emps";
    }

    //去员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmployee(@PathVariable("id")Integer id,Model model){
        //查出原来的数据
        Employee employee=employeeMapper.queryEmployeeById(id);
        System.out.println(employee);
        model.addAttribute("emp",employee);
        //查询所有部门信息
        List<Department> departments=departmentMapper.queryDepartments();
        System.out.println(departments);
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        System.out.println(employee);
        employeeMapper.updateEmployee(employee);
        return "redirect:/emps";
    }
    @GetMapping("/delemp/{id}")
    public String toDeleteEmp(@PathVariable("id")Integer id){
        employeeMapper.deleteEmployee(id);
        return "redirect:/emps";
    }

    @RequestMapping("/user/logout")
    public String UserLogout(HttpSession session){
        session.invalidate();
        return "redirect:/index.html";
    }
}
