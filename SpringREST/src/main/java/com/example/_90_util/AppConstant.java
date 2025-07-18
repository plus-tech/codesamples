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
	public static final String API_HEAD = "api.head";

	public static final String API_ROOT = "/rest";
	public static final String REST_DPT_FINDALL = "/findalldpts";
	public static final String REST_DPT_FINDBYID = "/findbyid/{department_id}";
	public static final String REST_DPT_INSERT = "/insertdpt";
	public static final String REST_DPT_UPDATE = "/updatedpt";
	public static final String REST_DPT_DELETE = "/deletedpt/{department_id}";

}
