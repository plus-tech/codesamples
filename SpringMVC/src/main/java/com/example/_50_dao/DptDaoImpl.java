package com.example._50_dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.example._60_dto.DptDto;

/*
 * Interfaces access the database
 */
@Repository
public class DptDaoImpl implements DptDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;	
	
	/*
	 * query the database with JdbcTemplate
	 */
	@Override
	public List<DptDto> findAll() {
		String sql = "SELECT department_id, department_name, manager_id "
				+ "FROM departments "
				+ "ORDER BY department_id";
    	
		List<DptDto> dptlist = null;
		try {
			dptlist = jdbcTemplate.query(sql,
					(rs, rowNum) -> new DptDto(rs.getLong("department_id"), 
							rs.getString("department_name"), 
							rs.getInt("manager_id"))
			);
		} catch (Exception e) {
			System.out.println(e);
		}
		
//		List<DptDto> dptlist = findAllDepartments();

		dptlist.forEach(dpt -> System.out.println(dpt.toString()));
		
		return dptlist;
	}

	public List<DptDto> findById(Long department_id){

        String sql = "SELECT department_id, department_name, manager_id "
        		+ "FROM departments "
        		+ "WHERE department_id = ?";
    	
        List<DptDto> dptlist = null;
        try {
	        	dptlist = jdbcTemplate.query(sql, new Object[] {department_id}, new int[] {Types.INTEGER}, new RowMapper<DptDto>() {
	            @Override
	            public DptDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	            		DptDto dptdto = new DptDto();
	            		dptdto.setDepartment_id(rs.getLong("department_id"));
	            		dptdto.setDepartment_name(rs.getString("department_name"));
	            		dptdto.setManager_id(rs.getInt("manager_id"));
	                return dptdto;
	            }
	        });
        } catch (Exception e) {
        		System.out.println(e);
        }
        
        return dptlist;
	}
	
	public void insertDpt(DptDto dptDto) {
	    String sql="insert into "
	    		+ "departments(department_id, department_name, manager_id) "
	    		+ "values (?, ?, ?)";
	
	    jdbcTemplate.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {	    	
                PreparedStatement stmt = con.prepareStatement(sql );
	            stmt.setLong(1, dptDto.getDepartment_id());
	            stmt.setString(2, dptDto.getDepartment_name());
	            stmt.setLong(3, dptDto.getManager_id());
	            return stmt;
           }
        });
	}
	
	public void updateDpt(DptDto dptDto) {
		
		String sql = "update departments "
				+ "set department_name = ?, manager_id = ? "
				+ "where department_id = ?";
		
		jdbcTemplate.update(sql, 
				dptDto.getDepartment_name(), dptDto.getManager_id(), 
				dptDto.getDepartment_id());
	}
	
	public void deleteDpt(Long department_id) {
		String sql = "delete from departments "
				+ "where department_id = ?";
		
		jdbcTemplate.update(sql, department_id);
	}
		
	/*
	 * query the database with DataSource
	 */
	@Autowired
	private DataSource dataSource;
	
	public List<DptDto> findAllDepartments(){
		List<DptDto> dptlist = new ArrayList<>();
		String sql = "SELECT department_id, department_name, manager_id "
				+ "FROM departments";
		
		try (Connection connection = this.dataSource.getConnection();
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
}
