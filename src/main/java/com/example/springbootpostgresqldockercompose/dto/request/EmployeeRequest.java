package com.example.springbootpostgresqldockercompose.dto.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String birthPlace;
}
