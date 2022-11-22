package com.isakayabasi.crudapplicationspringboot.controller;

import com.isakayabasi.crudapplicationspringboot.model.Department;
import com.isakayabasi.crudapplicationspringboot.service.DepartmentService;
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
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //display list of departments
    @GetMapping("/DepartmentList")
    public String viewDepartments(Model model){
        List<Department> departmentList = departmentService.getAllDepartment();
        model.addAttribute("listDepartments", departmentList);
        return "indexDepartment";
    }
    //display list of departments
    @GetMapping("/DepartmentList2")
    public String viewDepartments2(Model model){
        List<Department> departmentList = departmentService.getAllDepartment();
        model.addAttribute("listDepartments2", departmentList);
        return "new_employee";
    }


    @GetMapping("/shownewDepartmentForm")
    public String shownewDepartmentForm(Model model){
        //create model attribute to bind form data
        Department department= new Department();
        model.addAttribute("department",department);
        return "new_department";

    }

    @PostMapping("/saveDepartment")
    public String saveDepartment(@Valid @ModelAttribute("department") Department department,BindingResult bindingResult){
        //save department to database
        try {
            if (bindingResult.hasErrors()){
                return "new_department";
            }else{
                departmentService.saveDepartment(department);
                return "redirect:/shownewDepartmentForm?success";
            }
        }catch (Exception e){
            return "redirect:/shownewDepartmentForm?error";
        }
    }

    @GetMapping("/showFormForUpdateDepartment/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){

        //get employee from the service
        Department department = departmentService.getDepartmentById(id);

        //set employee as a model attribute to pre-populate the form
        model.addAttribute("department",department);
        return "update_department";
    }
    @PostMapping("/update_department")
    public String updateDepartment(@Valid @ModelAttribute("department") Department department,BindingResult bindingResult){
        //save department to database
        try {
            if (bindingResult.hasErrors()){
                return "update_department";
            }else{
                departmentService.updateDepartment(department);
                return "update_department";
            }
        }catch (Exception e){
            return "redirect:/shownewDepartmentForm?error";
        }
    }


    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable (value = "id") long id){

        // call delete employee

        this.departmentService.deleteDepartmentById(id);
        return "redirect:/DepartmentList";
    }
}
