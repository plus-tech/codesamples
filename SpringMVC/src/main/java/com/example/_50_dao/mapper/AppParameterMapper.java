/**
 * 
 */
package com.example._50_dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

import com.example._60_dto.*;
/**
 * 
 */
@Mapper
public interface AppParameterMapper {
	Parameters getParameters(String appName);
}
