package com.example._30_service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example._40_bizlogic.EmpBizLogic;
import com.example._50_dao.EmpDao;
import com.example._50_dao.sndmapper.SndEmployeeMapper;
import com.example._60_dto.EmpDptDto;
import com.example._60_dto.EmpDto;

/*
 * Acts as the boundary of transaction, especially for a distributed system
 * App logics for sub-systems are implemented in this layer if applied
 * Biz logics shall be removed from this layer and correspondingly included 
 * in the biz layers of each sub-system
 */
@Service
public class EmpService{
	
	@Autowired
	EmpBizLogic empBizLogic;
	
	/*
	 * For the purpose of demo, call DAO from the service layer,
	 * bypassing the business layer
	 */
	@Autowired
	EmpDao empDao;
	
	@Autowired
	SndEmployeeMapper empMapper;
	
	
	public List<EmpDto> findAll() {
//		return empDao.findAll();
		
		return empMapper.findAll();
		
//		return empBizLogic.findAll();
	}
	
	public EmpDto findById(Integer employee_id) {
		return empBizLogic.findById(employee_id);
	};
	
	@Transactional
	public void insertEmp(EmpDto empDto) {
		empBizLogic.insertEmp(empDto);
	};
    
	@Transactional
	public void updateEmp(EmpDto empDto) {
		empBizLogic.updateEmp(empDto);
    }
    
	@Transactional
    public void deleteEmp(Integer employee_id) {
    	empBizLogic.deleteEmp(employee_id);
    }
	
	public List<Map<String, Object>> leftJoin() {
		return empBizLogic.leftJoin();
	}
	
	public List<EmpDptDto> empLeftJoinDpt(){
		return empBizLogic.empLeftJoinDpt();
	}
}