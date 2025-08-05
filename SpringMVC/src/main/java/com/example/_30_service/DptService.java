package com.example._30_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example._40_bizlogic.DptBizLogic;
import com.example._50_dao.mapper.DepartmentMapper;
import com.example._60_dto.DptDto;

/*
 * Acts as the boundary of transaction, especially for a distributed system
 * App logics for sub-systems are implemented in this layer if applied
 * Biz logics shall be removed from this layer and correspondingly included 
 * in the biz layers of each sub-system
 */
@Service
public class DptService{
	
	@Autowired
	DptBizLogic dptBizLogic;
	
	/*
	 * For the purpose of demo, call DAO from the service layer,
	 * bypassing the business layer
	 */
	@Autowired
	DepartmentMapper dptMapper;	
	
	@Transactional
	public List<DptDto> findAll() {
		return dptMapper.findAll();
//		return dptBizLogic.findAll();
	}
	
	@Transactional
	public List<DptDto> findById(Long department_id) {
		return dptBizLogic.findById(department_id);
	}
	
	@Transactional
	public void insertDpt(DptDto dptDto) {
		dptBizLogic.insertDpt(dptDto);
	}
	
	@Transactional
	public void updateDpt(DptDto dptDto) {
		dptBizLogic.updateDpt(dptDto);
	}
	
	@Transactional
	public void deleteDpt(Long department_id) {
		dptBizLogic.deleteDpt(department_id);
	}
}