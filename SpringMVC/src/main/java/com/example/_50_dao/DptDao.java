package com.example._50_dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example._60_dto.DptDto;

@Repository
public interface DptDao {
	public List<DptDto> findAll();
	public List<DptDto> findById(Long department_id);
	public void insertDpt(DptDto dptDto);
	public void updateDpt(DptDto dptDto);
	public void deleteDpt(Long department_id);
}
