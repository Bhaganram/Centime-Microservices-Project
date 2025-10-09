
package com.centime.l1.mainservice.client.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonRequestLoggingProcessor implements Processor {

	private final ObjectMapper objectMapper;

	public CommonRequestLoggingProcessor(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Request Properties: {}", exchange.getProperties());
		log.info("Camel Call Route ID: {}", exchange.getFromRouteId());
		if(exchange.getIn().getHeaders()!=null && !exchange.getIn().getHeaders().isEmpty()){
			log.info("Request Headers: {}", this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(exchange.getIn().getHeaders()));
		}
		if(exchange.getIn().getBody()!=null){
			log.info("Request Body: {}", this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(exchange.getIn().getBody()));
		}

	}
}
