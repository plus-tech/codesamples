/**
 * 
 */
package com.example._40_dao.testmapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

import com.example._50_dto.*;
/**
 * 
 */
@Mapper
public interface TestDepartmentMapper {
	List<DptDto> findAll();
	List<DptDto> findById(Long department_id);
    void insertDpt(DptDto dptDto);
    void updateDpt(DptDto dptDto);
    void deleteDpt(Long department_id);
}
