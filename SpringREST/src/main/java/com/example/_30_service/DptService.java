package com.example._30_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example._40_dao.DptDao;
import com.example._40_dao.mapper.DepartmentMapper;
import com.example._40_dao.sndmapper.SndDepartmentMapper;
import com.example._50_dto.DptDto;

/*
 * 
 */
@Service
public class DptService{

	@Autowired
	DptDao dptDao;
	
	@Autowired
	DepartmentMapper dptMapper;
	
	public List<DptDto> findAll() {
		List<DptDto> dptlist = dptDao.findAll();
//		List<DptDto> dptlist = dptMapper.findAll();
		
		return dptlist;
	}
	
	public List<DptDto> findById(Long department_id){
		return dptDao.findById(department_id);
	}
	
	@Transactional(value="transactionManager")
	public void insertDpt(DptDto dptDto) {
		dptDao.insertDpt(dptDto);
	}
	
	@Transactional(value="transactionManager")
	public void updateDpt(DptDto dptDto) {
		dptDao.updateDpt(dptDto);
	}
	
	@Transactional(value="transactionManager")
	public void deleteDpt(Long department_id) {
		dptDao.deleteDpt(department_id);
	}
}