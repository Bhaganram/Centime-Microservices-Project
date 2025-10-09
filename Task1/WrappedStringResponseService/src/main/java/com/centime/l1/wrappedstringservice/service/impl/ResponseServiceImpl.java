
package com.centime.l1.wrappedstringservice.service.impl;

import com.centime.l1.wrappedstringservice.service.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResponseServiceImpl implements ResponseService {

	/**
	 * Returns a string
	 */
	@Override
	public String getWrappedString() {
		return "Hello";
	}

}
