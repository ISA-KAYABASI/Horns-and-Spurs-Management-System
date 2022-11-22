package com.hornsandspurs.hornsandspursmanagementsystem.Impl;

import com.hornsandspurs.hornsandspursmanagementsystem.model.Department;
import com.hornsandspurs.hornsandspursmanagementsystem.model.Employee;
import com.hornsandspurs.hornsandspursmanagementsystem.repository.DepartmentRepository;
import com.hornsandspurs.hornsandspursmanagementsystem.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Log4j2
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;



    private Employee newEmployee() {
        Date date = new Date();
        Employee employee = Employee.builder()
                .firstName("yusuf")
                .lastName("kayabasi")
                .salary(2444.23)
                .birthday(date)
                .isActive(true)
                .build();
        return employeeRepository.save(employee);
    }


    @Test
    public void saveEmployee() {
        Employee employee = new Employee();

        //First name first letter to uppercase rest is lowercase
        int firstnamelength = employee.getFirstName().length();
        employee.setFirstName(employee.getFirstName().substring(0,1).toUpperCase()+(employee.getFirstName().substring(1,firstnamelength).toLowerCase()));

        //Last name first letter to uppercase rest is lowercase
        int lastnameLength = employee.getLastName().length();
        employee.setLastName(employee.getLastName().substring(0,1).toUpperCase()+(employee.getLastName().substring(1,lastnameLength).toLowerCase()));

        employeeRepository.save(employee);

        assertNotNull(employeeRepository.findById(1L).get());
    }

    @Test
    void testTheFirstNameUpperCase(){
        String firstName ="sAnhCez";
        int firstnamelength = firstName.length();
        firstnamelength = Integer.parseInt((
                firstName.substring(0,1).toUpperCase()+
                (firstName.substring(1,firstnamelength).toLowerCase())));
        String currentFirstName = String.valueOf(firstnamelength);
        assertEquals("Sanchez", currentFirstName);
    }


    @Test
    void getAllEmployees() {
        List<Employee> emplist = employeeRepository.findAll();
        assertThat(emplist).size().isGreaterThan(0);
    }

    @Test
    void updateEmployee() {
        Employee employee = employeeRepository.findById(1L).get();

        //First name first letter to uppercase rest is lowercase
        int firstnamelength = employee.getFirstName().length();
        employee.setFirstName(employee.getFirstName().substring(0,1).toUpperCase()+(employee.getFirstName().substring(1,firstnamelength).toLowerCase()));

        //Last name first letter to uppercase rest is lowercase
        int lastnameLength = employee.getLastName().length();
        employee.setLastName(employee.getLastName().substring(0,1).toUpperCase()+(employee.getLastName().substring(1,lastnameLength).toLowerCase()));

        employee.setFirstName("isa");
          employeeRepository.save(employee);
        assertEquals("isa",employeeRepository.findById(1L).get().getFirstName());
    }


    @Test
    void getEmployeeById() {

        Employee employee = new Employee();
        employeeRepository.save(employee);

        Employee result = employeeRepository.findById(employee.getId()).get();
        assertEquals(employee.getId(), result.getId());

    }

    @Test
    void deleteEmployeeById() {
        Employee employee = new Employee();
        employeeRepository.save(employee);

        employeeRepository.deleteById(employee.getId());
        List<Employee> result = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 0);
    }

    @Test
    void getDepartmentsByName() {
        Department department = Department.builder()
                .departmentName("Finance")
                .build();
        departmentRepository.save(department);

        Department result = departmentRepository.findByDepartmentName(department.getDepartmentName());
        assertEquals(department.getDepartmentName(), result.getDepartmentName());
    }


}