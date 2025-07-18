package com.example._40_dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example._50_dto.DptDto;

@Repository
public interface DptDao {
	public List<DptDto> findAll();
	public DptDto createDpt(DptDto dptDto);
	public DptDto updateDpt(DptDto dptDto);
	public void deleteDpt(Long department_id);
}
