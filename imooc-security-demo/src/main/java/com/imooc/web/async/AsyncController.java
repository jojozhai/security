/**
 * 
 */
package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author zhailiang
 *
 */
@RestController
@RequestMapping("/order")
public class AsyncController {
	
	@Autowired
	private DeferredResultSet deferredResultSet;
	
	@Autowired
	private MockQueue mockQueue;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/1")
	public DeferredResult<String> order1() {
		logger.info("主线程开始");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultSet.getResults().put(orderNumber, result);
		mockQueue.setCompleteOrder(orderNumber);
		logger.info("主线程返回");
		return result;
	}
	
	
	@GetMapping
	public String order() throws Exception {
		
		long start = System.currentTimeMillis();
		
		System.out.println("主线程开始");
		
		Callable<String> result = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("副线程开始");
				Thread.sleep(1000);
				System.out.println("副线程返回，耗时:"+(System.currentTimeMillis() - start));
				return "hello";
			}
		};
		
		String str = result.call();
		System.out.println("主线程返回,耗时:"+ (System.currentTimeMillis() - start));
		
		return str;
		
	}

}
