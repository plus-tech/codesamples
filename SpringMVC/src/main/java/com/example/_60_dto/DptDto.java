package com.example._60_dto;

import java.io.Serializable;
import org.springframework.context.annotation.Bean;

//import org.springframework.data.relational.core.mapping.Table;

public class DptDto {
	private Long department_id;
	private String department_name;
	private Integer manager_id;
	
	public DptDto() {}
	public DptDto(Long department_id, String department_name, Integer manager_id) {
		this.department_id = department_id;
		this.department_name = department_name;
		this.manager_id = manager_id;
	}

	@Override
	public String toString() {
		return String.format(
				"Department[department_id=%d, department_name='%s', manager_id=%d]",
				department_id, department_name, manager_id);
	}

	public long getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(long department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Integer getManager_id() {
		return manager_id;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	
}
