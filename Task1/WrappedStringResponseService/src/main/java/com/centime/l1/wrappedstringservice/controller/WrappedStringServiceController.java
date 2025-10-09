
package com.centime.l1.wrappedstringservice.controller;

import com.centime.l1.wrappedstringservice.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller class to handle REST endpoints for wrapped string response
 * @author Bhagan Ram
 */
@RestController
@Slf4j
@RequestMapping("/rest/v1")
public class WrappedStringServiceController {

	private final ResponseService responseService;

	public WrappedStringServiceController(ResponseService responseService) {
		this.responseService = responseService;
	}

	/**
    * Invokes the ResponseService to get the wrapped string response
    */
	@GetMapping("/wrapped-response")
	public ResponseEntity<String> getWrappedString()  {
		return new ResponseEntity<>(responseService.getWrappedString(), HttpStatus.OK);
	}

}
