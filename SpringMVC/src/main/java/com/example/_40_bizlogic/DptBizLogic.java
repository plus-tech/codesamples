package com.example._40_bizlogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example._50_dao.DptDao;
import com.example._50_dao.mapper.DepartmentMapper;
import com.example._60_dto.DptDto;

/*
 * Business logics are implemented in this layer
 * Scope: this app
 */
@Service
public class DptBizLogic{
	@Autowired
	DptDao dptDao;
	
	@Autowired
	DepartmentMapper dptMapper;
	
	public List<DptDto> findAll() {
//		return dptDao.findAll();
		return dptMapper.findAll();
	}
	
	public List<DptDto> findById(Long department_id){
		return dptMapper.findById(department_id);
	}
	
	public void insertDpt(DptDto dptDto) {
		dptMapper.insertDpt(dptDto);
	}
	
	public void updateDpt(DptDto dptDto) {
		dptMapper.updateDpt(dptDto);
	}
	
	public void deleteDpt(Long department_id) {
		dptMapper.deleteDpt(department_id);
	}
}