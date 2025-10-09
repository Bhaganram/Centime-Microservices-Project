
package com.centime.l1.mainservice.client.route;

import com.centime.l1.mainservice.client.constants.AppConstants;
import com.centime.l1.mainservice.client.processor.CommonHeaderProcessor;
import com.centime.l1.mainservice.client.processor.CommonRequestLoggingProcessor;
import com.centime.l1.mainservice.client.processor.CommonResponseLoggingProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;


@Component
public class ClientServiceRoute extends RouteBuilder {

	private static final String SERVICE2_REST_BASE_URL = "{{centime.wrapped-string-service.rest.base}}";
	private static final String SERVICE3_REST_BASE_URL = "{{centime.concatenating-service.rest.base}}";
	private static final String REST_V1 = "/rest/v1";


	private final CommonRequestLoggingProcessor commonRequestLoggingProcessor;
	private final CommonResponseLoggingProcessor commonResponseLoggingProcessor;
	private final CommonHeaderProcessor commonHeaderProcessor;

	public ClientServiceRoute(CommonRequestLoggingProcessor commonRequestLoggingProcessor, CommonResponseLoggingProcessor commonResponseLoggingProcessor, CommonHeaderProcessor commonHeaderProcessor) {
		this.commonRequestLoggingProcessor = commonRequestLoggingProcessor;
		this.commonResponseLoggingProcessor = commonResponseLoggingProcessor;
		this.commonHeaderProcessor = commonHeaderProcessor;
	}
	@Override
	public void configure() throws Exception {

		from(AppConstants.GET_WRAPPED_STRING_ENDPOINT)
			.process(commonHeaderProcessor)
			.process(commonRequestLoggingProcessor)
			.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
				.toD(SERVICE2_REST_BASE_URL + REST_V1 + AppConstants.GET_WRAPPED_STRING_URL)
				.process(commonResponseLoggingProcessor).routeId("WRAPPED_STRING_ROUTE");

		from(AppConstants.CONCATENATE_STRING_ENDPOINT)
				.process(commonHeaderProcessor)
				.process(commonRequestLoggingProcessor)
				.setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http.HttpMethods.POST))
				.marshal().json()
				.toD(SERVICE3_REST_BASE_URL + REST_V1 + AppConstants.CONCATENATE_STRING_URL)
				.process(commonResponseLoggingProcessor).routeId("CONCATENATE_STRING_ROUTE");
	}


}
