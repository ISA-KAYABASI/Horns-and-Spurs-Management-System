package com.isakayabasi.crudapplicationspringboot.repository;

import com.isakayabasi.crudapplicationspringboot.CrudApplicationSpringbootApplication;
import com.isakayabasi.crudapplicationspringboot.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = CrudApplicationSpringbootApplication.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee getEmployee() {

        Employee employee = new Employee();

        employee.setFirstName("isa");
        employee.setLastName("Kayabasi");
        employee.setSalary(1000.00);
        employee.setActive(true);
        employee.setDepartmentName("Finance");

        return employee;
    }

    @Test
    public void testFindById() {
        Employee employee = getEmployee();
        employeeRepository.save(employee);

        Employee result = employeeRepository.findById(employee.getId()).get();
        assertEquals(employee.getId(), result.getId());
    }

    @Test
    public void testFindAll() {
        Employee employee = getEmployee();
        employeeRepository.save(employee);

        List<Employee> result = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 5);
    }

    @Test
    public void testSave() {
        Employee employee = getEmployee();
        employeeRepository.save(employee);
        Employee found = employeeRepository.findById(employee.getId()).get();
        assertEquals(employee.getId(), found.getId());
    }

    @Test
    public void testDeleteById() {
        Employee employee = getEmployee();
        employeeRepository.save(employee);

        employeeRepository.deleteById(employee.getId());
        List<Employee> result = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 0);
    }





}