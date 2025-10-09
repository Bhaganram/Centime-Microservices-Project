
package com.centime.l1.mainservice.client.impl;

import com.centime.l1.mainservice.client.ClientService;
import com.centime.l1.mainservice.client.constants.AppConstants;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

	private final ProducerTemplate producerTemplate;

	public ClientServiceImpl(ProducerTemplate producerTemplate) {
		this.producerTemplate = producerTemplate;
	}

	public String getWrappedString(){
		return producerTemplate.requestBodyAndHeaders(AppConstants.GET_WRAPPED_STRING_ENDPOINT, null, null, String.class);
	}

	public String concatenatedString(Map<String,Object> requestJson){
		return producerTemplate.requestBodyAndHeaders(AppConstants.CONCATENATE_STRING_ENDPOINT, requestJson, null, String.class);
	}
}
