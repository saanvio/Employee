package com.ing.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ing.employee.dto.ApplicationResponse;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.dto.UpdateEmployeeDto;
import com.ing.employee.entity.Employee;
import com.ing.employee.exception.CommonException;
import com.ing.employee.exception.EmployeeNotFoundException;
import com.ing.employee.repository.IEmployeeRepository;
import com.ing.employee.util.EmployeeConstants;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	IEmployeeRepository iEmployeeRepository;

	@Override
	public ApplicationResponse addEmploee(EmployeeDto employeeDto) {
		LOGGER.info("Add employee service");

		if (!emailvalidation(employeeDto.getEmail()))
			throw new CommonException(EmployeeConstants.ERROR_EMAIL_MESSAGE);
		if (!phoneNumberValidatoin(employeeDto.getPhoneNumber()))
			throw new CommonException(EmployeeConstants.ERROR_PHONE_NUMBER_MESSAGE);

		Optional<Employee> employeeExist = iEmployeeRepository.findByEmail(employeeDto.getEmail());
		if (employeeExist.isPresent())
			throw new EmployeeNotFoundException(EmployeeConstants.ERROR_EMPLOYEE_ALREADY_EXIST);

		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		iEmployeeRepository.save(employee);

		return new ApplicationResponse(HttpStatus.CREATED.value(), EmployeeConstants.CREATED_MESSAGE,null,null);
	}

	@Override
	public ApplicationResponse updateEmployee(Long employeeId, UpdateEmployeeDto employeeDto) {

		LOGGER.info("update employee service");
		Optional<Employee> employee = iEmployeeRepository.findById(employeeId);
		if (!employee.isPresent())
			throw new EmployeeNotFoundException(EmployeeConstants.ERROR_EMPLOYEE_NOT_FOUND_MESSAGE);

		BeanUtils.copyProperties(employeeDto, employee.get());
		iEmployeeRepository.save(employee.get());

		return new ApplicationResponse(HttpStatus.CREATED.value(), EmployeeConstants.UPDATED_MESSAGE,null,null);
	}

	@Override
	public ApplicationResponse getEmployeeById(Long employeeId) {

		LOGGER.info("get employeeby id service");
		Optional<Employee> employee = iEmployeeRepository.findById(employeeId);
		if (!employee.isPresent())
			throw new EmployeeNotFoundException(EmployeeConstants.ERROR_EMPLOYEE_NOT_FOUND_MESSAGE);
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(employee.get(), employeeDto);
		return new ApplicationResponse(HttpStatus.OK.value(), EmployeeConstants.UPDATED_MESSAGE,employeeDto,null);
	}

	@Override
	public ApplicationResponse getEmployees() {

		LOGGER.info("Employeess list");
		List<EmployeeDto> employeeDtosList = new ArrayList<>();
		List<Employee> employeesList = iEmployeeRepository.findAll();
		if (!employeesList.isEmpty()) {
			employeesList.stream().forEach(p -> {
				EmployeeDto employeeDto = new EmployeeDto();
				BeanUtils.copyProperties(p, employeeDto);
				employeeDtosList.add(employeeDto);
			});
		}

		return new ApplicationResponse(HttpStatus.OK.value(), EmployeeConstants.FETCHED_MESSAGE,null,employeeDtosList);
	}

	@Override
	public ApplicationResponse deleteEmployee(Long employeeId) {
		LOGGER.info("delete employee service");
		Optional<Employee> employee = iEmployeeRepository.findById(employeeId);
		if (!employee.isPresent())
			throw new EmployeeNotFoundException(EmployeeConstants.ERROR_EMPLOYEE_NOT_FOUND_MESSAGE);
		iEmployeeRepository.deleteById(employeeId);
		return new ApplicationResponse(HttpStatus.OK.value(), EmployeeConstants.DELETED_MESSAGE,null,null);
	}

	private boolean phoneNumberValidatoin(Long number) {

		String num = number.toString();
		Pattern p = Pattern.compile("^[0-9]{10}$");
		Matcher m = p.matcher(num);
		return (m.find() && m.group().equals(num));
	}

	private boolean emailvalidation(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

}
