
package com.centime.l1.mainservice.client;

import java.util.Map;

public interface ClientService {

	String getWrappedString();
	String concatenatedString(Map<String,Object> requestJson);
}
