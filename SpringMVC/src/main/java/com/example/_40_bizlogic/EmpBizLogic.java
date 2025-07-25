package com.example._40_bizlogic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example._50_dao.EmpDao;
import com.example._50_dao.mapper.EmployeeMapper;
import com.example._60_dto.EmpDptDto;
import com.example._60_dto.EmpDto;

/*
 * Business logics are implemented in this layer
 * Scope: this app
 */
@Service
public class EmpBizLogic{
	
	@Autowired
	EmployeeMapper empMapper;
	
	@Autowired
	EmpDao empDao;  // connect to the secondary data source
	
	public List<EmpDto> findAll() {
//		return empMapper.findAll();
		
		return empDao.findAll();
	}
	
	public EmpDto findById(Integer employee_id) {
		return empMapper.findById(employee_id);
	};
	
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