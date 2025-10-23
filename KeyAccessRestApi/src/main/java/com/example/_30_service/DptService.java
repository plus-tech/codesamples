package com.example._30_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example._40_dao.DptDao;
import com.example._40_dao.mapper.DptMapper;
import com.example._50_dto.DptDto;

/*
 * 
 */
@Service
public class DptService{

	@Autowired
	DptDao dptDao;
	
	@Autowired
	DptMapper dptMapper;
	
	
	public List<DptDto> findAllDpts() {
		List<DptDto> dptlist = dptDao.findAllDpts();
//		List<DptDto> dptlist = dptMapper.findAllDpts();
		
		return dptlist;
	}
	
	public List<DptDto> findById(Long dpt_id){
		return dptDao.findById(dpt_id);
	}
	
	@Transactional
	public void insertDpt(DptDto dptDto) {
		dptDao.insertDpt(dptDto);
	}
	
	@Transactional
	public void updateDpt(DptDto dptDto) {
		dptDao.updateDpt(dptDto);
	}
	
	@Transactional
	public void deleteDpt(Long dpt_id) {
		dptDao.deleteDpt(dpt_id);
	}
}