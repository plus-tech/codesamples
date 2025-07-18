package com.example._30_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example._40_dao.DptDao;
import com.example._40_dao.mapper.DepartmentMapper;
import com.example._40_dao.testmapper.TestDepartmentMapper;
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
//	TestDepartmentMapper dptMapper;
	
	public List<DptDto> findAll() {
		List<DptDto> dptlist;
		
//		dptlist = dptDao.findAll();
		dptlist = dptMapper.findAll();
		
		return dptlist;
	}
	
	public List<DptDto> findById(Long department_id){
		return dptMapper.findById(department_id);
	}
	
	@Transactional
	public void insertDpt(DptDto dptDto) {
		dptMapper.insertDpt(dptDto);
	}
	
	@Transactional
	public void updateDpt(DptDto dptDto) {
		dptMapper.updateDpt(dptDto);
	}
	
	@Transactional
	public void deleteDpt(Long department_id) {
		dptMapper.deleteDpt(department_id);
	}
}