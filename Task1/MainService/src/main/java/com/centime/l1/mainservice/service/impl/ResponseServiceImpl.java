
package com.centime.l1.mainservice.service.impl;

import com.centime.l1.mainservice.client.ClientService;
import com.centime.l1.mainservice.service.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ResponseServiceImpl implements ResponseService {

	private final ClientService clientService;

	public ResponseServiceImpl(ClientService clientService){
		this.clientService = clientService;
	}
	/**
	 * Returns a string indicating the service is running
	 */
	@Override
	public String getServiceRunningStatus() {
		return "UP";
	}

	/**
	 * Returns a string by concatenating response from host
	 */
	@Override
	public String concatenateHostResponse(Map<String, Object> requestJson) {
		// Example implementation: Concatenate all string values in the map
		String responseFromHost1 = clientService.getWrappedString();
		String responseFromHost2 = clientService.concatenatedString(requestJson);
        return (responseFromHost1 + " " + responseFromHost2).trim();
	}
}
