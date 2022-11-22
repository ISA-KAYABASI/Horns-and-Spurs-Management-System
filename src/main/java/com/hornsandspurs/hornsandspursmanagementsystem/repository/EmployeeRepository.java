package com.isakayabasi.crudapplicationspringboot.repository;

import com.isakayabasi.crudapplicationspringboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


@Query(value = "SELECT  * FROM `isa123`.`employees` WHERE first_name like %:keyword% OR last_name LIKE %:keyword%", nativeQuery = true)
    List<Employee> findByKeyWord(@Param("keyword")String keyword);

}
