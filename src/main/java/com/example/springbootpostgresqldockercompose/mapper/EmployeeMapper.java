package com.example.springbootpostgresqldockercompose.mapper;

import com.example.springbootpostgresqldockercompose.dto.request.EmployeeRequest;
import com.example.springbootpostgresqldockercompose.dto.response.EmployeeResponse;
import com.example.springbootpostgresqldockercompose.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
    EmployeeResponse mapEntityToResponse(Employee employee);

    Employee mapRequestToEntity(EmployeeRequest employeeRequest);

    void update(@MappingTarget Employee entity, EmployeeRequest employeeRequest);

}
