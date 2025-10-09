
package com.centime.l1.commons.filter;

import com.centime.l1.commons.model.TraceId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class TracingFilter extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.application.name:application}")
    private String applicationName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("traceID", this.getTraceIdFromHeader(request));
        filterChain.doFilter(request, response);
        this.cleanUp();
    }

    @Override
    public void destroy() {
        MDC.clear();
    }

    private void cleanUp() {
        MDC.clear();

    }

    private String getTraceIdFromHeader(final HttpServletRequest request) {
        TraceId traceId = new TraceId();
        String traceIdString = request.getHeader("X-Trace-ID");
        if (traceIdString == null || traceIdString.isEmpty()) {
            traceId.setUid(UUID.randomUUID().toString());
            traceId.setAppId(applicationName);

        }else{
            traceId.setUid(getUid(traceIdString));
            traceId.setAppId(applicationName);
        }

        return formCorrelationId(traceId);
    }

    private String getUid(String traceIdString) {
        try{
            return objectMapper.readValue(traceIdString,TraceId.class).getUid();

        } catch (JsonProcessingException e) {
            return "";
        }
    }


    private String formCorrelationId(TraceId traceId) {
        String traceIdString = "";

        try {
            traceIdString = objectMapper.writeValueAsString(traceId);
        } catch (JsonProcessingException exception) {
            log.error("Error While parsing Correlation Id Schema to String", exception);
        }

        log.info("Trace Id for the request: {}", traceIdString);

        return traceIdString;
    }
}
