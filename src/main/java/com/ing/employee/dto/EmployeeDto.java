package com.ing.employee.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeName;
	private String address;
	private Long phoneNumber;
	private String email;
}
