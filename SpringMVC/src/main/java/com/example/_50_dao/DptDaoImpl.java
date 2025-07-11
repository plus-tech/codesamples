package com.example._50_dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example._50_dao.DptDao;
import com.example._60_dto.DptDto;

/*
 * Interfaces access the database
 */
@Repository
public class DptDaoImpl implements DptDao {
	
	@Autowired
	@Qualifier("jdbcTemplateOracle")
	private JdbcTemplate jdbcTemplateOracle;
	
	public List<DptDto> getAllDpts(){
		String sql = "SELECT department_id, department_name, manager_id FROM departments";
	    	
		List<DptDto> dptlist = null;
		try {
			dptlist = jdbcTemplateOracle.query(sql,
					(rs, rowNum) -> new DptDto(rs.getLong("department_id"), rs.getString("department_name"), rs.getInt("manager_id"))
			);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return dptlist;
	}
	
	/*
	 * JdbcTemplate - injected the primary
	 */
	@Autowired
	@Qualifier("jdbcTemplateMore")
	private JdbcTemplate jdbcTemplateMore;
	
	public List<DptDto> getAllDepartments() {

        String sql = "SELECT department_id, department_name, manager_id FROM departments WHERE department_id = 10";
    	
        return jdbcTemplateMore.query(sql, new RowMapper<DptDto>() {
            @Override
            public DptDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            		DptDto dptdto = new DptDto();
            		dptdto.setDepartment_id(rs.getLong("department_id"));
            		dptdto.setDepartment_name(rs.getString("department_name"));
            		dptdto.setManager_id(rs.getInt("manager_id"));
                return dptdto;
            }
        });
	}
	
	/*
	 * Using a DataSource to query the database
	 */
	@Autowired
	@Qualifier("dataSourceOracle")
	private DataSource dataSource;
	
	public List<DptDto> getAllDpt(){
		List<DptDto> dptlist = new ArrayList<>();
		String sql = "SELECT department_id, department_name, manager_id FROM departments WHERE department_id = 10";
		
		try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {
			
            while (rs.next()) {
            		DptDto dpt = new DptDto(rs.getLong("department_id"), rs.getString("department_name"), rs.getInt("manager_id"));
            		dptlist.add(dpt);
            }
			
	    } catch (SQLException e) {
	        // Handle the exception
	    }
		
		return dptlist;
	}
	
	@Override
	public List<DptDto> getAll() {
		List<DptDto> dptlist = getAllDpts();
//		List<DptDto> dptlist = getAllDepartments();
//		List<DptDto> dptlist = getAllDpt();

		dptlist.forEach(dpt -> System.out.println(dpt.toString()));
		
		return dptlist;
	}
}
