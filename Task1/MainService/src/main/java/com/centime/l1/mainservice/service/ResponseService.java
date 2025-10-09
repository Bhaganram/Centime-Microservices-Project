
package com.centime.l1.mainservice.service;

import java.util.Map;

public interface ResponseService {

	/**
    * Returns a string indicating the service is running
    */
	public String getServiceRunningStatus();

	/**
	 * Returns a String by concatenating the response from host services
	 * @param requestJson input json
	 * @return concatenated String
	 */
	public String concatenateHostResponse(Map<String,Object> requestJson);

}
