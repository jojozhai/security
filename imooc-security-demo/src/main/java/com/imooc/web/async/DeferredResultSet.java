/**
 * 
 */
package com.imooc.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author zhailiang
 *
 */
@Component
public class DeferredResultSet {
	
	private Map<String, DeferredResult<String>> results = new HashMap<>();

	public Map<String, DeferredResult<String>> getResults() {
		return results;
	}

	public void setResults(Map<String, DeferredResult<String>> results) {
		this.results = results;
	}
	
}
