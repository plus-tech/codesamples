package com.example._40_dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Repository;

import com.example._50_dto.DptDto;


@Repository
public class UsersDaoImpl implements UsersDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	public List<UserDetails> findAllUsers(){
////		select u.username, password, substr(authority, 6) role from users u, authorities a where u.enabled=1 and u.username=a.username;
//		
//        String sql = "SELECT u.username, password, replace(authority, 'ROLE_', '') role "
//        		+ "FROM users u, authorities a "
//        		+ "WHERE u.enabled=1"
//        		+ "  AND u.username=a.username";
//        
//        List<UserDetails> listUsers = null;
//        try {
//	        listUsers = jdbcTemplate.query(sql, new RowMapper<UserDetails>() {
//	            @Override
//	            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
//	            	
//	        			UserDetails user = User.withUsername(rs.getString("username"))
//					.password(rs.getString("password"))
//					.roles(rs.getString("role"))
//					.build();
//	            		
//	        			System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.getAuthorities());
//	                return user;
//	            }
//	        });
//        } catch (Exception e) {
//        		System.out.println(e);
//	    }
//        
//        return listUsers;
//	}
}
