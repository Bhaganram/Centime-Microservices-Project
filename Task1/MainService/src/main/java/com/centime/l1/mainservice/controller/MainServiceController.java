
package com.centime.l1.mainservice.controller;

import com.centime.l1.mainservice.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Controller class to handle REST endpoints for service status and
 * host response concatenation
 * @author Bhagan Ram
 */
@RestController
@Slf4j
@RequestMapping("/rest/v1")
public class MainServiceController {

	private final ResponseService responseService;

	public MainServiceController(ResponseService responseService) {
		this.responseService = responseService;
	}

	/**
    * Invokes the StatusService to get the running status of the service
    */
	@GetMapping("/running-status")
	public ResponseEntity<String> getServiceRunningStatus()  {
		return new ResponseEntity<>(responseService.getServiceRunningStatus(), HttpStatus.OK);
	}

	/**
	 * Invokes the StatusService to get the concatenated response from host services
	 */
	@PostMapping("/concatenate-host-response")
	public ResponseEntity<String> concatenateHostResponse(@RequestBody Map<String,Object> requestJson)  {
		return new ResponseEntity<>(responseService.concatenateHostResponse(requestJson), HttpStatus.OK);
	}

}
