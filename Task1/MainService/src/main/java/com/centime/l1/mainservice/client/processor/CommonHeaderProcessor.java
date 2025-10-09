package com.centime.l1.mainservice.client.processor;

import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class CommonHeaderProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {

        Map<String, Object> headersMap = new HashMap();
        headersMap.put("X-Trace-ID", MDC.get("traceID"));

        exchange.getIn().getHeaders().putAll(headersMap);
    }
}
