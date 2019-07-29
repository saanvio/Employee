package com.ing.employee.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;
	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "PHONE_NUMBER")
	private Long phoneNumber;
	@Column(name = "EMPLOYEE_EMAIL")
	private String email;

}
