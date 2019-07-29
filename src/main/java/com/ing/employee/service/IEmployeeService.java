package com.ing.employee.service;

import java.util.List;

import com.ing.employee.dto.ApplicationResponse;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.dto.UpdateEmployeeDto;

public interface IEmployeeService {

	public ApplicationResponse addEmploee(EmployeeDto employeeDto);
	
	public ApplicationResponse updateEmployee(Long employeeId,UpdateEmployeeDto employeeDto);
	
	public EmployeeDto getEmployeeById(Long employeeId);
	
	public List<EmployeeDto> getEmployees();
	
	public ApplicationResponse deleteEmployee(Long employeeId);

}
