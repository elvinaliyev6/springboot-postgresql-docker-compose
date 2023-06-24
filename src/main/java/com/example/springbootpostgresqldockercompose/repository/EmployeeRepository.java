package com.example.springbootpostgresqldockercompose.repository;

import com.example.springbootpostgresqldockercompose.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {


}
