
package com.centime.l1.dbconnectionservice.service.impl;

import com.centime.l1.dbconnectionservice.annotation.LogMethodParam;
import com.centime.l1.dbconnectionservice.entity.NameToColorMappingEntity;
import com.centime.l1.dbconnectionservice.model.NameToColorMappingHierarchyResponseVO;
import com.centime.l1.dbconnectionservice.model.NameToColorMappingRequestVO;
import com.centime.l1.dbconnectionservice.model.NameToColorMappingResponseVO;
import com.centime.l1.dbconnectionservice.repository.NameToColorMappingRepository;
import com.centime.l1.dbconnectionservice.service.NameToColorMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NameToColorMappingServiceImpl implements NameToColorMappingService {

	private final NameToColorMappingRepository nameToColorMappingRepository;

	public NameToColorMappingServiceImpl(NameToColorMappingRepository nameToColorMappingRepository){
		this.nameToColorMappingRepository = nameToColorMappingRepository;
	}

	/**
	 * Create a NameToColorMappingEntity and save into the database
	 * @param request request DTO
	 * @return NameToColorMappingResponseVO
	 */
	@LogMethodParam
	@Override
	public NameToColorMappingResponseVO createNameToColorMappingEntity(NameToColorMappingRequestVO request) {
		NameToColorMappingEntity entity = new NameToColorMappingEntity();
		entity.setColor(request.getColor());
		entity.setName(request.getName());
		entity.setParentId(request.getParentId());
		NameToColorMappingEntity response = nameToColorMappingRepository.save(entity);
		return NameToColorMappingResponseVO.builder().color(response.getColor()).name(response.getName()).id(response.getId()).parentId(response.getParentId()).build();
	}

	/**
	 * Fetches the NameToColorMappingEntity for the given unique ID.
	 * @param id uniqueId
	 * @return NameToColorMappingResponseVO
	 */
	@LogMethodParam
	@Override
	public NameToColorMappingResponseVO getNameToColorMappingEntity(Long id) {
		NameToColorMappingEntity response = nameToColorMappingRepository.findById(id).get();
		return NameToColorMappingResponseVO.builder().name(response.getName()).color(response.getColor()).parentId(response.getParentId()).id(response.getId()).build();
	}

	@LogMethodParam
	@Override
	public List<NameToColorMappingHierarchyResponseVO> getNameToColorMappingEntitiesList() {
		List<NameToColorMappingEntity> entitiesList = nameToColorMappingRepository.findAll();
		Map<Long, NameToColorMappingHierarchyResponseVO> map = new HashMap<>();
		for(NameToColorMappingEntity entity : entitiesList){
			map.put(entity.getId(), new NameToColorMappingHierarchyResponseVO(entity.getName()));
		}

		List<NameToColorMappingHierarchyResponseVO> response = new ArrayList<>();
		for(NameToColorMappingEntity entity : entitiesList){
			if(entity.getParentId()==0){
				response.add(map.get(entity.getId()));
			}else{
				NameToColorMappingHierarchyResponseVO parentEntity = map.get(entity.getParentId());
				if(parentEntity!=null){
					parentEntity.addSubClasses(map.get(entity.getId()));
				}

			}
		}
		return response;
	}
}
