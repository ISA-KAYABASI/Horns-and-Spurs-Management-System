package com.isakayabasi.crudapplicationspringboot.controller;

import com.isakayabasi.crudapplicationspringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "indexEmployee";
    }

}