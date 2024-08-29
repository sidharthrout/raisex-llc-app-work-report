package com.usermanagement.service;

import com.usermanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee>getAllEmployee();
    void saveEmployee(Employee employee);
    Employee updateEmployee (int id);
    void deleteEmployee(int id);

}
