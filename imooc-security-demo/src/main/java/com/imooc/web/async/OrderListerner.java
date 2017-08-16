/**
 * 
 */
package com.imooc.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component
public class OrderListerner {

	@Autowired
	private MockQueue queue;

	@Autowired
	private DeferredResultSet deferredResultSet;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public MockQueue getQueue() {
		return queue;
	}

	public void setQueue(MockQueue queue) {
		this.queue = queue;
	}

	@Scheduled(fixedDelay = 1000)
	public void listenCompleteOrder() throws InterruptedException {
		
		while (true) {
			if (StringUtils.isNotBlank(queue.getCompleteOrder())) {
				String orderNumber = queue.getCompleteOrder();
				logger.info("开始处理订单:" + orderNumber);
				Thread.sleep(1000);
				logger.info("订单处理完毕:" + orderNumber);
				deferredResultSet.getResults().get(queue.getCompleteOrder()).setResult("ok");
				queue.setCompleteOrder(null);
			} else {
				Thread.sleep(100);
			}
		}
	}

}
