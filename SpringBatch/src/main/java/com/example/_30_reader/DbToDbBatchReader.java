package com.example._30_reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.example._70_dto.User;


@Configuration
public class DbToDbBatchReader {
	
	@Autowired
	DataSource dataSource;
	
	
	@Qualifier("bcp")
	@Bean
	@StepScope
	public JdbcCursorItemReader<User> dtdImportUserStepReader() {
		String sql = "SELECT u.username, u.password, a.authority, u.enabled "
				+ "FROM users u, authorities a "
				+ "WHERE u.username=a.username";
		
		return new JdbcCursorItemReaderBuilder<User>()
				.dataSource(this.dataSource)
				.name("userReader")
				.sql(sql)
				.rowMapper(new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new User(
								rs.getString("username"),
								rs.getString("password"),
								rs.getString("authority"),
								rs.getInt("enabled"));
					}
					
				})
				.build();
	}

}
