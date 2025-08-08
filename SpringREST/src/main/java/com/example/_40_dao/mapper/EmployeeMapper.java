/**
 * 
 */
package com.example._40_dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

import com.example._50_dto.*;
/**
 * 
 */
@Mapper
public interface EmployeeMapper {
	List<EmpDto> findAll();
	List<EmpDto> findById(Integer employee_id);
    void insertEmp(EmpDto empDto);
    void updateEmp(EmpDto empDto);
    void deleteEmp(Integer employee_id);
    
    List<Map<String, Object>> leftJoin();
    List<EmpDptDto> empLeftJoinDpt();
}
