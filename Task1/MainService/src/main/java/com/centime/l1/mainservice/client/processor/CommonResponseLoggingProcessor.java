
package com.centime.l1.mainservice.client.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonResponseLoggingProcessor implements Processor {

	private final ObjectMapper objectMapper;

	public CommonResponseLoggingProcessor(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Camel Call Route ID: {}", exchange.getFromRouteId());
		log.debug("Response Properties: {}", exchange.getProperties());
		if(exchange.getIn().getHeaders()!=null && !exchange.getIn().getHeaders().isEmpty()){
			log.info("Response Headers: {}", this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(exchange.getIn().getHeaders()));
		}
		if (exchange.getException() == null) {
			if(exchange.getIn().getBody()!=null){
				log.info("Response Body: {}", this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(exchange.getIn().getBody(String.class)));
			}
		} else {
			log.error("Host call failed for the route : {}", exchange.getFromRouteId());
		}

	}

}
