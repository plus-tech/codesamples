package com.example._40_dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example._50_dto.ApiKeyDto;


@Repository
public class ApiKeyDaoImpl implements ApiKeyDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<String> getApiKeys() {
		String sql = "SELECT key, username, description "
				+ "FROM app_apikey "
				+ "ORDER BY username";
	    	
		List<ApiKeyDto> keylist = null;
		try {
			keylist = jdbcTemplate.query(sql,
					(rs, rowNum) -> new ApiKeyDto(
							rs.getString("key"),
							rs.getString("username"),
							rs.getString("description"))
			);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		List<String> validKeys = new ArrayList<String>();
		if (keylist != null && !keylist.isEmpty()) {
			keylist.forEach(key -> {
//				System.out.println(">> api key: %s".formatted(key.getKey()));
				validKeys.add(key.getKey());
			});
		}
      
		return validKeys;
	}

}
