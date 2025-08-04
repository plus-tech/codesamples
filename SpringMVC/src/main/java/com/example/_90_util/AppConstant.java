package com.example._90_util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 
 */
public final class AppConstant {
	public static final String APP_NAME = "SpringMVC";
	public static final String APP_TITLE = "app.title";
	public static final String APP_HEAD = "app.head";
	
	/*
	 * Names of views 
	 */
	public static final String VIEW_INDEX = "index";
	public static final String VIEW_LOGIN = "login";
	public static final String VIEW_MAINPAGE = "mainpage";
	public static final String VIEW_DEPARTMENT = "departments";
	public static final String VIEW_EMPLOYEE = "employees";
	public static final String VIEW_UPLOADFILE = "uploadfile";
	
	/*
	 * Paths for handlers
	 */
	public static final String PATH_DPT_FINDALL = "/findalldpts";
	public static final String PATH_DPT_FINDBYID = "/finddptbyid/{department_id}";
	public static final String PATH_DPT_INSERT = "/insertdpt";
	public static final String PATH_DPT_UPDATE = "/updatedpt";	
	public static final String PATH_DPT_DELETE = "/deletedpt/{department_id}";
	
	public static final String PATH_EMP_FINDALL = "/findallemps";
	public static final String PATH_EMP_FINDBYID = "/findempbyid/{employee_id}";
	public static final String PATH_EMP_INSERT = "/insertemp";
	public static final String PATH_EMP_UPDATE = "/updateemp";	
	public static final String PATH_EMP_DELETE = "/deleteemp/{employee_id}";
	
	/*
	 * Upload a file
	 */
	public static final String UPLOAD_SELECTFILE = "Please select a file to upload.";
	public static final String UPLOAD_SUCCESS = "File uploaded successfully: {0}";
	public static final String UPLOAD_FAILED = "Failed to upload file: {0}";	
	
	/*
	 * REST API root
	 */
	public static final String REST_ROOT = "/rest";

	public static final String API_DPT_FINDALL = PATH_DPT_FINDALL;
	public static final String API_DPT_FINDBYID = PATH_DPT_FINDBYID;
	public static final String API_DPT_INSERT = PATH_DPT_INSERT;
	public static final String API_DPT_UPDATE = PATH_DPT_UPDATE;	
	public static final String API_DPT_DELETE = PATH_DPT_DELETE;
	
	/*
	 * DB access
	 */
	public static final String DB_ACCESS_MYBATIS = "Mybatis";
	public static final String DB_ACCESS_DAO = "DAO";
	public static final String DB_PRIMARY = "Primary";
	public static final String DB_SECONDARY = "Secondary";
	
}
