package com.ing.employee.service;

import com.ing.employee.dto.ApplicationResponse;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.dto.UpdateEmployeeDto;

public interface IEmployeeService {

	public ApplicationResponse addEmploee(EmployeeDto employeeDto);
	
	public ApplicationResponse updateEmployee(Long employeeId,UpdateEmployeeDto employeeDto);
	
	public ApplicationResponse getEmployeeById(Long employeeId);
	
	public ApplicationResponse getEmployees();
	
	public ApplicationResponse deleteEmployee(Long employeeId);

}
