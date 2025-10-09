
package com.centime.l1.dbconnectionservice.service;

import com.centime.l1.dbconnectionservice.annotation.LogMethodParam;
import com.centime.l1.dbconnectionservice.model.NameToColorMappingHierarchyResponseVO;
import com.centime.l1.dbconnectionservice.model.NameToColorMappingRequestVO;
import com.centime.l1.dbconnectionservice.model.NameToColorMappingResponseVO;

import java.util.List;


public interface NameToColorMappingService {

	NameToColorMappingResponseVO createNameToColorMappingEntity(NameToColorMappingRequestVO request);

	NameToColorMappingResponseVO getNameToColorMappingEntity(Long id);

	List<NameToColorMappingHierarchyResponseVO> getNameToColorMappingEntitiesList();
}
