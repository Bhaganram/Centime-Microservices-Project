
package com.centime.l1.dbconnectionservice.controller;

import com.centime.l1.dbconnectionservice.model.NameToColorMappingHierarchyResponseVO;
import com.centime.l1.dbconnectionservice.model.NameToColorMappingRequestVO;
import com.centime.l1.dbconnectionservice.model.NameToColorMappingResponseVO;
import com.centime.l1.dbconnectionservice.service.NameToColorMappingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * Controller class to handle REST endpoints for name to color mapping entity
 * @author Bhagan Ram
 */
@RestController
@Slf4j
@RequestMapping("/rest/v1")
public class NameToColorMappingController {

	private final NameToColorMappingService nameToColorMappingService;

	public NameToColorMappingController(NameToColorMappingService nameToColorMappingService) {
		this.nameToColorMappingService = nameToColorMappingService;
	}

	/**
    * Invokes the nameToColorMappingService and stores the request data into database
    */
	@PostMapping("/name-to-color-mapping")
	public ResponseEntity<NameToColorMappingResponseVO> createNameToColorMappingEntity(@RequestBody NameToColorMappingRequestVO request)  {
		return new ResponseEntity<>(nameToColorMappingService.createNameToColorMappingEntity(request), HttpStatus.CREATED);
	}


	/**
	 * Invokes the nameToColorMappingService and fetches the entity with given id
	 */
	@GetMapping("/name-to-color-mapping/{id}")
	public ResponseEntity<NameToColorMappingResponseVO> getNameToColorMappingEntity(@PathVariable(name = "id") Long id)  {
		return new ResponseEntity<>(nameToColorMappingService.getNameToColorMappingEntity(id), HttpStatus.OK);
	}

	/**
	 * Invokes the nameToColorMappingService and fetches all the entities with their name to color mappings
	 */
	@GetMapping("/name-to-color-mappings")
	public ResponseEntity<List<NameToColorMappingHierarchyResponseVO>> getNameToColorMappingEntitiesList()  {
		return new ResponseEntity<>(nameToColorMappingService.getNameToColorMappingEntitiesList(), HttpStatus.OK);
	}

}
