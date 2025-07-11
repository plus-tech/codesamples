package com.example._60_dao;

import java.util.List;

import org.springframework.batch.core.ExitStatus;

import com.example._70_dto.User;

public interface UsersDao {
	
	public ExitStatus truncateUsers();
	public int[] bulkInsertUsers(List<User> itemList);
	
}
