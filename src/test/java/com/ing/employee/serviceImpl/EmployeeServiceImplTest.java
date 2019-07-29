package com.ing.employee.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.ing.employee.dto.ApplicationResponse;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.dto.UpdateEmployeeDto;
import com.ing.employee.entity.Employee;
import com.ing.employee.exception.EmployeeNotFoundException;
import com.ing.employee.repository.IEmployeeRepository;
import com.ing.employee.service.EmployeeServiceImpl;
import com.ing.employee.util.EmployeeConstants;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {
	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	IEmployeeRepository iEmployeeRepository;

	@Test
	public void addEmployee_1Test() {
		Employee employee = getEmployee();
		EmployeeDto employeeDto = getEmployeeDto();
		// Mockito.when(iEmployeeRepository.save(Mockito.any())).thenReturn(employee);
		ApplicationResponse actual = employeeServiceImpl.addEmploee(employeeDto);
		Assert.assertEquals(EmployeeConstants.CREATED_MESSAGE, actual.getMessage());

	}

	@Test(expected = EmployeeNotFoundException.class)
	public void addEmployee_2Test() {
		Employee employee = getEmployee();
		EmployeeDto employeeDto = getEmployeeDto();
		Mockito.when(iEmployeeRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(employee));
		ApplicationResponse actual = employeeServiceImpl.addEmploee(employeeDto);
		Assert.assertEquals(EmployeeConstants.CREATED_MESSAGE, actual.getMessage());

	}

	@Test
	public void getEmployeeById_1Test() {
		Employee employee = getEmployee();
		Mockito.when(iEmployeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		EmployeeDto empActual = employeeServiceImpl.getEmployeeById(1L);
		Assert.assertEquals("priya", empActual.getEmployeeName());
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void getEmployeeById_2Test() {
		// Employee employee=getEmployee();
		// Mockito.when(iEmployeeRepository.findById(3L)).thenReturn(Optional.of(employee));
		EmployeeDto empActual = employeeServiceImpl.getEmployeeById(5L);
		Assert.assertEquals("priya", empActual.getEmployeeName());
	}

	@Test
	public void getEmployeesTest() {
		Employee employee = getEmployee();
		EmployeeDto employeeDto = getEmployeeDto();

		List<EmployeeDto> employeeDtosList = new ArrayList<>();
		employeeDtosList.add(employeeDto);

		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);

		Mockito.when(iEmployeeRepository.findAll()).thenReturn(employeeList);
		List<EmployeeDto> empActual = employeeServiceImpl.getEmployees();
		Assert.assertEquals(employeeDtosList.size(), empActual.size());
	}

	@Test
	public void deleteById_Test() {
		Employee employee = getEmployee();
		Mockito.when(iEmployeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		ApplicationResponse actual = employeeServiceImpl.deleteEmployee(1L);
		Assert.assertEquals(EmployeeConstants.DELETED_MESSAGE, actual.getMessage());
	}

//	@Test(expected = EmployeeNotFoundException.class)
//	public void deleteById_2Test() {
//		Employee employee = getEmployee();
//		Mockito.when(iEmployeeRepository.findById(3L)).thenReturn(Optional.of(employee));
//		ApplicationResponse actual = employeeServiceImpl.deleteEmployee(3L);
//		Assert.assertEquals(EmployeeConstants.DELETED_MESSAGE, actual.getMessage());
//	}

//	@Test
//	public void updateEmployee_1Test()
//	{
//		Employee employee=getEmployee();
//		UpdateEmployeeDto updateEmployeeDto=getUpdateEmployeeDto();
//	//	BeanUtils.copyProperties(updateEmployeeDto, employee);
//		Mockito.when(iEmployeeRepository.save(employee)).thenReturn(employee);
//		ApplicationResponse actual=employeeServiceImpl.updateEmployee(employee.getEmployeeId(), updateEmployeeDto);
//		Assert.assertEquals(EmployeeConstants.UPDATED_MESSAGE, actual.getMessage());
//	}

//	@Test(expected = EmployeeNotFoundException.class)
//	public void updateEmployee_2Test()
//	{
//		Employee employee=getEmployee();
//		UpdateEmployeeDto employeeDto=getUpdateEmployeeDto();
//		Mockito.when(iEmployeeRepository.findById(2L)).thenReturn(Optional.of(employee));
//		ApplicationResponse actual=employeeServiceImpl.updateEmployee(employee.getEmployeeId(), employeeDto);
//		Assert.assertEquals(EmployeeConstants.UPDATED_MESSAGE, actual.getMessage());
//	}

	public Employee getEmployee() {
		return new Employee(1L, "priya", "Hyd", 2342342342L, "h@gmail.com");
	}

	public EmployeeDto getEmployeeDto() {
		return new EmployeeDto("priya", "Hyd", 2342342342L, "h@gmail.com");
	}

	public UpdateEmployeeDto getUpdateEmployeeDto() {
		return new UpdateEmployeeDto("hari", "BAnglore", 2342342342L);
	}

}
