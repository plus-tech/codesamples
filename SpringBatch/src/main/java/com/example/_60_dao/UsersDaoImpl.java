package com.example._60_dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example._70_dto.User;

@Repository
public class UsersDaoImpl implements UsersDao{
	
//	@Qualifier("test")
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public ExitStatus truncateUsers() {
		String truncSql = "truncate table users";
		
		jdbcTemplate.execute(truncSql);
		return ExitStatus.COMPLETED;
	}
	
	public int[] bulkInsertUsers(List<User> itemList){
		
        String sql = "INSERT INTO users (id, username, password, role) VALUES (users_seq.nextval, ?, ?, ?)";
//        List<User> entities = new ArrayList<>();
        // Populate entities list
        int itemNum[] = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User entity = itemList.get(i);
                ps.setString(1, entity.getUsername());
                ps.setString(2, entity.getPassword());
                ps.setString(3, entity.getRole());
            }

            @Override
            public int getBatchSize() {
                return itemList.size();
            }
        });
        
		return itemNum;
	}
}

/*
INSERT INTO your_table (column1, column2, column3) VALUES (?, ?, ?)
String sql = "INSERT INTO your_table (column1, column2, column3) VALUES (?, ?, ?)";
List<Object[]> batchArgs = new ArrayList<>();
// Add data to batchArgs, e.g., batchArgs.add(new Object[]{"value1", "value2", "value3"});
jdbcTemplate.batchUpdate(sql, batchArgs);
*/