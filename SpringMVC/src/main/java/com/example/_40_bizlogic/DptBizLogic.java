package com.example._40_bizlogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example._50_dao.DptDao;
import com.example._50_dao.mapper.AppParameterMapper;
import com.example._50_dao.mapper.DepartmentMapper;
import com.example._60_dto.DptDto;
import com.example._90_util.AppConstant;
import com.example._90_util.AppParameters;

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
		return dptMapper.findAll();
//		return dptDao.findAll();
	}
	
	public List<DptDto> findById(Long department_id){
		AppParameters.getInstance().setDptDbAccess(AppConstant.DB_ACCESS_DAO);
//		AppParameters.getInstance().setDptDbAccess(AppConstant.DB_ACCESS_MYBATIS);
		
		if (AppParameters.getInstance().getDptDbAccess().equals(AppConstant.DB_ACCESS_DAO)) {
			System.out.println(">> findById: DAO access");
			
			return dptDao.findById(department_id);
		} else if (AppParameters.getInstance().getDptDbAccess().equals(AppConstant.DB_ACCESS_MYBATIS)) {
			System.out.println(">> findById: Mybatis access");
			
			return dptMapper.findById(department_id);
		} else {
			System.out.println(">> findById: default Mybatis access");
			
			return dptMapper.findById(department_id);
		}
	}
	
	public void insertDpt(DptDto dptDto) {
		dptDao.insertDpt(dptDto);
	}
	
	public void updateDpt(DptDto dptDto) {
		dptDao.updateDpt(dptDto);
	}
	
	public void deleteDpt(Long department_id) {
		dptDao.deleteDpt(department_id);
	}
}