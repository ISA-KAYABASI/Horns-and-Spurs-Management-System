package com.isakayabasi.crudapplicationspringboot.repository;

import com.isakayabasi.crudapplicationspringboot.CrudApplicationSpringbootApplication;
import com.isakayabasi.crudapplicationspringboot.model.Department;
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
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department getDepartment() {

        Department department = new Department();
        department.setDepartmentName("Finance");
        department.setDepartmentName("Sales");

        return departmentRepository.save(department);
    }

    @Test
    public void testSave() {
        Department department = getDepartment();
        departmentRepository.save(department);
        Department found = departmentRepository.findById(department.getId()).get();
        assertEquals(department.getId(), found.getId());
    }
    @Test
    public void testFindAll() {
        Department department = getDepartment();
        departmentRepository.save(department);

        List<Department> result = new ArrayList<>();
        departmentRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 1);
    }

    @Test
    public void testFindById() {
        Department department = getDepartment();
        departmentRepository.save(department);

        Department result = departmentRepository.findById(department.getId()).get();
        assertEquals(department.getId(), result.getId());
    }

    @Test
    public void testDeleteById() {
        Department department = getDepartment();
        departmentRepository.save(department);

        departmentRepository.deleteAll();
        List<Department> result = new ArrayList<>();
        departmentRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 0);
    }
}