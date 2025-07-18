package com.example._90_util;

import org.springframework.stereotype.Component;

/*
 * 
 */
public final class AppConstant {
	public static final String APP_TITLE = "app.title";
	public static final String APP_HEAD = "app.head";
	
	/*
	 * Define the names of views 
	 */
	public static final String VIEW_INDEX = "index";
	public static final String VIEW_LOGIN = "login";
	public static final String VIEW_MAINPAGE = "mainpage";
	public static final String VIEW_DEPARTMENT = "departments";
	public static final String VIEW_EMPLOYEE = "employees";
	public static final String VIEW_UPLOADFILE = "uploadfile";
	
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
}
