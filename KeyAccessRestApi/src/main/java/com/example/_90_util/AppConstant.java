package com.example._90_util;


/*
 * 
 */
public final class AppConstant {
	public static final String APP_HEAD = "app.head";
	
	public static final String API_ACCESS_KEY = "api.access.key";
	
	public static final int EXIT_NORMAL = 0;
	public static final int EXIT_ERROR = 1;

	public static final String REST_ROOT = "/rest";
	
	public static final String API_DPT_FINDALL = "/findalldpts";
	public static final String API_DPT_FINDBYID = "/finddptbyid/{dpt_id}";
	public static final String API_DPT_INSERT = "/insertdpt";
	public static final String API_DPT_UPDATE = "/updatedpt";
	public static final String API_DPT_DELETE = "/deletedpt/{dpt_id}";
	public static final String URI_DPT_CREATED ="/finddptbyid/%d";
	
	public static final String API_KEY_HEADER = "Api-Key";
	public static final String APPUTIL_ROOT = "/apputil";
	public static final String API_GEN_KEY = "/genkey";
	
//	public static final String API_EMP_FINDALL = "/findallemps";
//	public static final String API_EMP_FINDBYID = "/findempbyid/{emp_id}";
//	public static final String API_EMP_INSERT = "/insertemp";
//	public static final String API_EMP_UPDATE = "/updateemp";
//	public static final String API_EMP_DELETE = "/deleteemp/{emp_id}";
//	public static final String URI_EMP_CREATED ="/finddptbyid/%d";
}
