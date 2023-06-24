package com.example.springbootpostgresqldockercompose.service;

import com.example.springbootpostgresqldockercompose.dto.request.EmployeeRequest;
import com.example.springbootpostgresqldockercompose.dto.response.EmployeeResponse;
import com.example.springbootpostgresqldockercompose.dto.response.GenericResponse;
import com.example.springbootpostgresqldockercompose.entity.Employee;
import com.example.springbootpostgresqldockercompose.exception.MyException;
import com.example.springbootpostgresqldockercompose.mapper.EmployeeMapper;
import com.example.springbootpostgresqldockercompose.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public GenericResponse<Void> addEmployee(EmployeeRequest employeeRequest) {
        Employee employee=employeeMapper.mapRequestToEntity(employeeRequest);
        employeeRepository.save(employee);
        return GenericResponse.success();
    }

    public GenericResponse<List<EmployeeResponse>> getAllEmployees() {
        List<Employee> employeeList=employeeRepository.findAll();
        if(employeeList.isEmpty()){
            throw new MyException("Employee not found");
        }
        List<EmployeeResponse> employeeResponseList=employeeList.stream()
                .map(employee -> employeeMapper.mapEntityToResponse(employee))
                .collect(Collectors.toList());
        return GenericResponse.success(employeeResponseList);
    }

    public GenericResponse<Void> updateEmployee(EmployeeRequest request, Long empId) {
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(() -> new MyException("Employee not found"));
        employeeMapper.update(employee,request);
        return GenericResponse.success();
    }

    public GenericResponse<EmployeeResponse> getEmployeeById(Long empId) {
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(() -> new MyException("Employee not found"));
        EmployeeResponse employeeResponse=employeeMapper.mapEntityToResponse(employee);
        return GenericResponse.success(employeeResponse);
    }

    public GenericResponse<Void> deleteEmployee(Long empId) {
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(() -> new MyException("Employee not found"));
        employeeRepository.delete(employee);
        return GenericResponse.success();
    }
}
