
package com.centime.l1.concatinationservice.service.impl;

import com.centime.l1.concatinationservice.service.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ResponseServiceImpl implements ResponseService {


	/**
	 * Returns a string by concatenating the values from request json
	 */
	@Override
	public String concatenateRequestToString(Map<String, Object> requestJson) {
		StringBuilder result = new StringBuilder();
		for (Object value : requestJson.values()) {
				result.append((String) value).append(" ");
		}
		return result.toString().trim();
	}
}
