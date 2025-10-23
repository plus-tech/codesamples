package com.example._40_dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example._50_dto.DptDto;

@Repository
public interface DptDao {
	public List<DptDto> findAllDpts();
	public List<DptDto> findById(Long dpt_id);
	public void insertDpt(DptDto dptDto);
	public void updateDpt(DptDto dptDto);
	public void deleteDpt(Long dpt_id);
}
