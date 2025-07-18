package com.example._50_dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//import org.springframework.data.relational.core.mapping.Table;

public class EmpDto {
	@NotNull
	private Integer employee_id;
	
	@NotNull
	@Pattern(regexp="[a-zA-Z]*")
	@Size(min=1, max=50, message="Name is too long")
	private String employee_name;
	
	private Double salary;
	
	@Size(min=10, max=10)
	@DateTimeFormat(pattern="yyyy/mm/dd")
	private String hire_date;
	
	private Integer manager_id;
	
	@NotNull
	private Long department_id;


	public EmpDto() {}
	public EmpDto(
				Integer employee_id,
				String employee_name, 
				Double salary,
				String hire_date,
				Integer manager_id,				
				Long department_id) {
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.salary = salary;
		this.hire_date = hire_date;
		this.manager_id = manager_id;
		this.department_id = department_id;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee[employee_id='%d', employee_name='%s', salary='%f', hire_date='%s', manager_id='%d', department_id=%d]",
				employee_id, employee_name, salary, hire_date, manager_id, department_id);
	}
	
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String department_name) {
		this.employee_name = department_name;
	}

	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public String getHire_date() {
		return hire_date;
	}
	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}
	
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}

	public long getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}
	
}
