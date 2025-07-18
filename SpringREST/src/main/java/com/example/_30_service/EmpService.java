package com.example._30_service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example._40_dao.mapper.EmployeeMapper;
import com.example._50_dto.EmpDptDto;
import com.example._50_dto.EmpDto;

/*
 *
 */
@Service
public class EmpService{
	
	@Autowired
	EmployeeMapper empMapper;
	
	public List<EmpDto> findAll() {
		return empMapper.findAll();
	}
	
	public EmpDto findById(Integer employee_id) {
		return empMapper.findById(employee_id);
	};
	
	@Transactional()
	public void insertEmp(EmpDto empDto) {
		empMapper.insertEmp(empDto);
	};
    
	public void updateEmp(EmpDto empDto) {
		empMapper.updateEmp(empDto);
    }
    
    public void deleteEmp(Integer employee_id) {
    	empMapper.deleteEmp(employee_id);
    }
    
    public List<Map<String, Object>> leftJoin(){
    		return empMapper.leftJoin();
    }
    
    public List<EmpDptDto> empLeftJoinDpt(){
    		return empMapper.empLeftJoinDpt();
    }
}