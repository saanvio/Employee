package com.ing.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.employee.dto.ApplicationResponse;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.dto.UpdateEmployeeDto;
import com.ing.employee.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	IEmployeeService iEmployeeService;

	@PostMapping("/add")
	public ResponseEntity<ApplicationResponse> createEmployee(@RequestBody EmployeeDto employeeDto) {
		LOGGER.info("addemployee controller");
		return new ResponseEntity<>(iEmployeeService.addEmploee(employeeDto), HttpStatus.CREATED);

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApplicationResponse> updateEmployee(@PathVariable Long id,@RequestBody UpdateEmployeeDto employeeDto) {
		LOGGER.info("update employee controller");
		return new ResponseEntity<>(iEmployeeService.updateEmployee(id,employeeDto), HttpStatus.CREATED);

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
		LOGGER.info("get employee by id controller");
		return new ResponseEntity<>(iEmployeeService.getEmployeeById(id), HttpStatus.OK);

	}
	
	@GetMapping()
	public ResponseEntity<List<EmployeeDto>> getEmployess() {
		LOGGER.info("get employees controller");
		return new ResponseEntity<>(iEmployeeService.getEmployees(), HttpStatus.OK);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApplicationResponse> deleteEmployee(@PathVariable Long id) {
		LOGGER.info("delete employee controller");
		return new ResponseEntity<>(iEmployeeService.deleteEmployee(id), HttpStatus.OK);

	}

}
