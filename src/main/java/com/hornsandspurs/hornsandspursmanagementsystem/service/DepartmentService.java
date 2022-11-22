package com.isakayabasi.crudapplicationspringboot.service;

import com.isakayabasi.crudapplicationspringboot.model.Department;

import java.util.List;


public interface DepartmentService{
    List<Department> getAllDepartment();
    Department saveDepartment(Department department);

    Department updateDepartment(Department department);
    Department getDepartmentById(long id);

    void deleteDepartmentById(long id);
}
