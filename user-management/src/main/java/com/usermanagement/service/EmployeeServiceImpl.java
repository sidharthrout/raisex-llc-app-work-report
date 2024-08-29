// EmployeeServiceImpl.java

package com.usermanagement.service;

import com.usermanagement.entity.Employee;
import com.usermanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        if (employee.getWorkStart() != null && employee.getWorkEnd() != null) {
            int workMinutes = (int) Duration.between(employee.getWorkStart(), employee.getWorkEnd()).toMinutes();
            int breakTime = Math.max(employee.getBreakTime(), 0); // Ensure break time is not negative
            int totalWorkTime = Math.max(workMinutes - breakTime, 0); // Ensure total work time is not negative

            int hours = totalWorkTime / 60;
            int minutes = totalWorkTime % 60;
            employee.setTotalWorkTime(String.format("%d:%02d", hours, minutes)); // Format as HH:MM
        }
        employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(int id) {
        Optional<Employee>optional=employeeRepository.findById(id);
        Employee employee =null;
        employee = optional.get();
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        this.employeeRepository.deleteById(id);
    }
}
