/**
 * 
 */
package com.example._50_dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

import com.example._60_dto.*;
/**
 * 
 */
@Mapper
public interface DepartmentMapper {
	List<DptDto> findAll();
	List<DptDto> findById(Long department_id);
	void insertDpt(DptDto dptDto);
	void updateDpt(DptDto dptDto);
	void deleteDpt(Long department_id);
}
