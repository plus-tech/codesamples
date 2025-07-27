package com.example._60_dto;

import com.example._90_util.AppConstant;
import com.example._90_util.AppParameters;

public class Parameters {
	String appName;

	/*
	 * DAO or Mybatis
	 */
	String dptDbAccess;
	String empDbAccess;
	/*
	 * Primary db or secondary db or embedded db
	 */
	String dptCurrentDb;
	String empCurrentDb;
	
	@Override
	public String toString() {
		return (String.format("AppParameters[appName=%s, dptDbAccess=%s, empDbAccess=%s, dptCurrentDb=%s, empCurrentDb=%s]", 
				appName, 
				dptDbAccess,
				empDbAccess,
				dptCurrentDb,
				empCurrentDb));
		
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
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
