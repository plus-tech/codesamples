package com.example._90_util;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/*
 * 
 */
public final class AppConstant {
	public static final String APP_HEAD = "app.head";

	public static final String REST_ROOT = "/rest";
	public static final String API_DPT_FINDALL = "/findalldpts";
	public static final String API_DPT_FINDBYID = "/finddptbyid/{department_id}";
	public static final String API_DPT_INSERT = "/insertdpt";
	public static final String API_DPT_UPDATE = "/updatedpt";
	public static final String API_DPT_DELETE = "/deletedpt/{department_id}";
	public static final String URI_DPT_CREATED ="/finddptbyid/%d";

	public static final String API_EMP_FINDALL = "/findallemps";
	public static final String API_EMP_FINDBYID = "/findempbyid/{employee_id}";
	public static final String API_EMP_INSERT = "/insertemp";
	public static final String API_EMP_UPDATE = "/updateemp";
	public static final String API_EMP_DELETE = "/deleteemp/{employee_id}";
	public static final String URI_EMP_CREATED ="/finddptbyid/%d";
}
