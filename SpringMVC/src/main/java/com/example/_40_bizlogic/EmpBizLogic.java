package com.example._40_bizlogic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example._50_dao.mapper.EmployeeMapper;
import com.example._60_dto.EmpDto;

/*
 * Business logics are implemented in this layer
 * Scope: this app
 */
@Service
public class EmpBizLogic{
	
	@Autowired
	EmployeeMapper empMapper;
	
	public List<EmpDto> getAll() {
		return empMapper.findAll();
	}
	
	public List<Map<String, Object>> leftJoin() {
		return empMapper.leftJoin();
	}
}