/**
 * 
 */
package com.example._50_dao.sndmapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

import com.example._60_dto.*;
/**
 * 
 */
@Mapper
public interface SndEmployeeMapper {
	List<EmpDto> findAll();
	EmpDto findById(Integer employee_id);
    void insertEmp(EmpDto empDto);
    void updateEmp(EmpDto empDto);
    void deleteEmp(Integer employee_id);
    
    List<Map<String, Object>> leftJoin();
    List<EmpDptDto> empLeftJoinDpt();
}
