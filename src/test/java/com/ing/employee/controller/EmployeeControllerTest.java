package com.ing.employee.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.employee.dto.EmployeeDto;
import com.ing.employee.dto.UpdateEmployeeDto;
import com.ing.employee.entity.Employee;
import com.ing.employee.service.IEmployeeService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class EmployeeControllerTest {

	@Mock
	IEmployeeService iEmployeeService;

	@InjectMocks
	EmployeeController employeeController;

	MockMvc mockMvc;
	Employee employee;
	EmployeeDto employeeDto;
	UpdateEmployeeDto updateEmployeeDto;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		employee = getEmployee();
		employeeDto = getEmployeeDto();
		updateEmployeeDto = getUpdateEmployeeDto();
	}

	@Test
	public void addEmployeTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/employee/add").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(asJsonString(employeeDto))).andExpect(status().isCreated());

	}

	@Test
	public void updateEmployeeTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/employee/update/{id}", employee.getEmployeeId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(updateEmployeeDto))).andExpect(status().isCreated());
	}

	@Test
	public void getEmployeeByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", employee.getEmployeeId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getEmployees() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employee").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteEmployeeTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{id}",employee.getEmployeeId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	public Employee getEmployee() {
		return new Employee(1L, "priya", "Hyd", 2342342342L, "h@gmail.com");
	}

	public EmployeeDto getEmployeeDto() {
		return new EmployeeDto("priya", "Hyd", 2342342342L, "h@gmail.com");
	}

	public UpdateEmployeeDto getUpdateEmployeeDto() {
		return new UpdateEmployeeDto("hari", "BAnglore", 2342342342L);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
