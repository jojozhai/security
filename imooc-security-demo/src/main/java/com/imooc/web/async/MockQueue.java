/**
 * 
 */
package com.imooc.web.async;

import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class MockQueue {
	
	private String placeOrder;
	
	private String completeOrder;

	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) {
		this.placeOrder = placeOrder;
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}

}
