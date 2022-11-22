//package com.isakayabasi.crudapplicationspringboot.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.isakayabasi.crudapplicationspringboot.model.Employee;
//import com.isakayabasi.crudapplicationspringboot.repository.EmployeeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class EmployeeControllerTest {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//
//    @BeforeEach
//    void setup(){
//        employeeRepository.deleteAll();
//    }
//
//
//    @Test
//    void saveEmployee() {
//        Employee employee = new Employee();
//        employee.setFirstName("isa");
//        employee.setLastName("Kayabasi");
//        employee.setSalary(1000.00);
//        employee.setActive(true);
//        employee.setDepartmentName("Finance");
//        employee.setBirthday(2022-11-16 21:00:00);
//
//        employeeRepository.save(employee);
//        assertEquals(isa, employeeRepository.exists());
//    }
//
//    @Test
//    void getEmployee() {
//    }
//
//    @Test
//    void updateEmployeeStatus() {
//    }
//
//    @Test
//    void updateEmployee() {
//    }
//
//    @Test
//    void deleteEmployee() {
//    }
//}