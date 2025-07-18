package com.example._50_dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example._60_dto.DptDto;

@Repository
public interface DptDao {
	public List<DptDto> findAll();
}
