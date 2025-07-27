/**
 * 
 */
package com.example._90_util;

import org.springframework.stereotype.Component;

/**
 * 
 */

@Component
public class AppParameters {
	private static AppParameters instance;
	
	/*
	 * DAO or Mybatis
	 */
	String dptDbAccess = AppConstant.DB_ACCESS_DAO;
	String empDbAccess = AppConstant.DB_ACCESS_MYBATIS;
	/*
	 * Primary db or secondary db or embedded db
	 */
	String dptCurrentDb = AppConstant.DB_PRIMARY;
	String empCurrentDb = AppConstant.DB_SECONDARY;
	
	
	private AppParameters() {}
	
	public static AppParameters getInstance() {
		if (instance == null) {
			instance = new AppParameters();
			return instance;
		}
		return instance;
	}

	public String getDptDbAccess() {
		return dptDbAccess;
	}
	public void setDptDbAccess(String dptDbAccess) {
		this.dptDbAccess = dptDbAccess;
	}

	public String getEmpDbAccess() {
		return empDbAccess;
	}
	public void setEmpDbAccess(String empDbAccess) {
		this.empDbAccess = empDbAccess;
	}

	public String getDptCurrentDb() {
		return dptCurrentDb;
	}
	public void setDptCurrentDb(String dptCurrentDb) {
		this.dptCurrentDb = dptCurrentDb;
	}

	public String getEmpCurrentDb() {
		return empCurrentDb;
	}
	public void setEmpCurrentDb(String empCurrentDb) {
		this.empCurrentDb = empCurrentDb;
	}
}
