/**
 * 
 */
package com.example._40_dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

import com.example._50_dto.*;
/**
 * 
 */
@Mapper
public interface DptMapper {
	List<DptDto> findAllDpts();
	List<DptDto> findById(Long dpt_id);
    void insertDpt(DptDto dptDto);
    void updateDpt(DptDto dptDto);
    void deleteDpt(Long dpt_id);
}
