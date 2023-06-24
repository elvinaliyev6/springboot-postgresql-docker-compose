package com.example.springbootpostgresqldockercompose.controller;

import com.example.springbootpostgresqldockercompose.dto.request.EmployeeRequest;
import com.example.springbootpostgresqldockercompose.dto.response.EmployeeResponse;
import com.example.springbootpostgresqldockercompose.dto.response.GenericResponse;
import com.example.springbootpostgresqldockercompose.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public GenericResponse<Void> addEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.addEmployee(employeeRequest);
    }

    @GetMapping
    public GenericResponse<List<EmployeeResponse>> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{empId}")
    public GenericResponse<Void> updateEmployee(@RequestBody EmployeeRequest request
            , @PathVariable Long empId){
        return employeeService.updateEmployee(request,empId);
    }

    @GetMapping("/{empId}")
    public GenericResponse<EmployeeResponse> getEmployeeById(@PathVariable Long empId){
        return employeeService.getEmployeeById(empId);
    }

    @DeleteMapping("/{empId}")
    public GenericResponse<Void> deleteEmployee(@PathVariable Long empId){
        return employeeService.deleteEmployee(empId);
    }

}
