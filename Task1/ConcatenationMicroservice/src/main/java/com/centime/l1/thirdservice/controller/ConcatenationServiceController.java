
package com.centime.l1.thirdservice.controller;

import com.centime.l1.thirdservice.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Controller class to handle REST endpoints for request concatenation
 * @author Bhagan Ram
 */
@RestController
@Slf4j
@RequestMapping("/rest/v1")
public class ConcatenationServiceController {

	private final ResponseService responseService;

	public ConcatenationServiceController(ResponseService responseService) {
		this.responseService = responseService;
	}

	/**
	 * Invokes the StatusService to get the concatenated response from host services
	 */
	@PostMapping("/concatenate-request")
	public ResponseEntity<String> concatenateHostResponse(@RequestBody Map<String,Object> requestJson)  {
		return new ResponseEntity<>(responseService.concatenateRequestToString(requestJson), HttpStatus.CREATED);
	}

}
