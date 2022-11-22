package com.isakayabasi.crudapplicationspringboot.service.Impl;


import com.isakayabasi.crudapplicationspringboot.model.Department;
import com.isakayabasi.crudapplicationspringboot.repository.DepartmentRepository;
import com.isakayabasi.crudapplicationspringboot.repository.EmployeeRepository;
import com.isakayabasi.crudapplicationspringboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) throws ArithmeticException  {



        if (departmentRepository.existsByDepartmentName(department.getDepartmentName())){
            throw new ArithmeticException("Same department already exists: " + department.getDepartmentName());
        }else
        {
        // Department name turn first letter uppercase
            int departmentNameLength = department.getDepartmentName().length();
            department.setDepartmentName(department.getDepartmentName().substring(0,1).toUpperCase()+(department.getDepartmentName().substring(1,departmentNameLength).toLowerCase()));
        return departmentRepository.save(department);
        }
    }

    @Override
    public Department updateDepartment(Department department) {
        if (departmentRepository.existsByDepartmentName(department.getDepartmentName())){
            throw new ArithmeticException("Same department already exists: " + department.getDepartmentName());
        }else
        {
            return departmentRepository.save(department);
        }
    }

    @Override
    public Department getDepartmentById(long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        Department department= null;
        if(optional.isPresent()){
            department = optional.get();
        }else {
            throw new RuntimeException("Department not found for id :: " + id);
        }
        return department;
    }
    @Override
    public void deleteDepartmentById(long id) {
        this.departmentRepository.deleteById(id);
    }


}
