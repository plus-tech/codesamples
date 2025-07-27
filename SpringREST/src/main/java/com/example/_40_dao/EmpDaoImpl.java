package com.example._40_dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example._50_dto.EmpDto;

@Repository
public class EmpDaoImpl implements EmpDao {
	
	@Qualifier("secondary")
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<EmpDto> findAll() {
        String sql = "SELECT employee_id, first_name, last_name, salary, hire_date, manager_id, department_id "
        		+ "FROM employees "
        		+ "ORDER BY employee_id";
        
        return jdbcTemplate.query(sql, new RowMapper<EmpDto>() {
            @Override
            public EmpDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            		EmpDto empDto = new EmpDto(
            				rs.getInt("employee_id"),
            				rs.getString("first_name"),
            				rs.getString("last_name"),
            				rs.getDouble("salary"),
            				rs.getString("hire_date"),
            				rs.getInt("manager_id"),
            				rs.getLong("department_id"));

                return empDto;
            }
        });	
	};

}
