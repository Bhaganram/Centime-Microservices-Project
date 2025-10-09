package com.centime.l1.commons.response;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerResponseHandler implements ResponseBodyAdvice {

	private final ObjectMapper objectMapper;

	public ControllerResponseHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		int responseStatusCode = 0;
		if (response instanceof ServletServerHttpResponse) {
			ServletServerHttpResponse res = (ServletServerHttpResponse) (response);
			responseStatusCode = res.getServletResponse().getStatus();
		}

		if (isSwaggerOrActuatorUri(request.getURI())
				|| responseStatusCode < HttpStatus.OK.value() || body instanceof Resource) {
			return body;
		}
		HttpHeaders headers = response.getHeaders();
		headers.add("traceID", MDC.get("traceID"));
		headers.add("channelContext",getResponseStatus(responseStatusCode));

		return body;
	}

	private String getResponseStatus(int responseStatusCode) {
		Map<String,String> successMessage = new HashMap<>();
		successMessage.put("status","SUCCESS");
		if(responseStatusCode == 200) {
			successMessage.put("code","default.success");
			successMessage.put("codeDescription","The request has been processed successfully");
		} else if(responseStatusCode==201){
			successMessage.put("code","default.created");
			successMessage.put("codeDescription","The resource has been created successfully");
		}else{
			successMessage.put("code","Failed");
			successMessage.put("codeDescription","Request has failed");
			successMessage.put("status","FAILED");
		}
		try {
			return objectMapper.writeValueAsString(successMessage);
		} catch (Exception e) {
			return "";
		}
	}

	private boolean isSwaggerOrActuatorUri(URI uri) {
		return uri.getPath().contains("/actuator") || uri.getPath().contains("swagger");
	}

}