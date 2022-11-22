package com.isakayabasi.crudapplicationspringboot.controller;

import com.isakayabasi.crudapplicationspringboot.model.Department;
import com.isakayabasi.crudapplicationspringboot.model.Employee;
import com.isakayabasi.crudapplicationspringboot.service.DepartmentService;
import com.isakayabasi.crudapplicationspringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;



    //display list of employees
    @GetMapping("/EmployeeList")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "indexEmployee";
    }

    //display list of employees
    @GetMapping("/searchEmployee")
    public String getEmployee(Model model, String keyword){
        if (keyword != null){
            model.addAttribute("listEmployees",employeeService.findByKeyWord(keyword));
        }else {
            model.addAttribute("listEmployees", employeeService.getAllEmployees());
        }
        return "indexEmployee";
    }


    @GetMapping("/showFormForDetails/{id}")
    public String showFormForDetails(@Valid @PathVariable (value = "id") long id, Model model){

        //get employee from the service
        Employee employee = employeeService.getEmployeeById(id);


        //set employee as a model attribute to pre-populate the form
        model.addAttribute("employeeDetails",employee);
        return "employee_details";
    }


    @PostMapping("/showFormForDetails/{id}")
    public String updateEmployeeStatus(@Valid @PathVariable (value = "id") long id, Model model){

        //get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        employee.setActive(!employee.isActive());
        employeeService.saveEmployee(employee);
        model.addAttribute("employeeDetails",employee);
        return "employee_details";
    }


    @GetMapping("/shownewEmployeeForm")
    public String shownewEmployeeForm(Model model){
        //create model attribute to bind form data
        Employee employee= new Employee();
        List<Department> listDepartments2 = departmentService.getAllDepartment();
        model.addAttribute("listDepartments2", listDepartments2);
        model.addAttribute("employee",employee);
        return "new_employee";
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,BindingResult bindingResult,Model model){

        List<Department> listDepartments2 = departmentService.getAllDepartment();
        model.addAttribute("listDepartments2", listDepartments2);

        if (bindingResult.hasErrors()){

            return "new_employee";
        }
        //save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/shownewEmployeeForm?successadd";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@Valid @PathVariable (value = "id") long id, Model model){


        //get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        employee.setActive(!employee.isActive());

        //set employee as a model attribute to pre-populate the form
        model.addAttribute("employee",employee);
        List<Department> listDepartments2 = departmentService.getAllDepartment();
        model.addAttribute("listDepartments2", listDepartments2);
        return "update_employee";
    }


    @PostMapping("/updateEmployee")
    public String updateEmployee(@Valid  @ModelAttribute("employee") Employee employee,BindingResult bindingResult,Model model){
        List<Department> listDepartments2 = departmentService.getAllDepartment();
        model.addAttribute("listDepartments2", listDepartments2);

        if (bindingResult.hasErrors()){

            return "update_employee";
        }
        employeeService.updateEmployee(employee);
        return "update_employee";

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id){

        // call delete employee

        this.employeeService.deleteEmployeeById(id);
        return "redirect:/EmployeeList";
    }
}
