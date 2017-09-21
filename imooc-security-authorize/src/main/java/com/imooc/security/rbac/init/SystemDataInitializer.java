/**
 * 
 */
package com.imooc.security.rbac.init;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 系统初始化器
 * 
 * @author zhailiang
 *
 */
@Component
public class SystemDataInitializer implements ApplicationListener<ContextRefreshedEvent>{
	
	/**
	 * 系统中所有的{@link DataInitializer}接口实现
	 */
	@Autowired(required = false)
	private List<DataInitializer> dataInitializers;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 循环调用系统中所有的{@link DataInitializer}
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(CollectionUtils.isNotEmpty(dataInitializers)){
			
			dataInitializers.sort((initor1, initor2) -> {
				return initor1.getIndex().compareTo(initor2.getIndex());
			});
			
			dataInitializers.stream().forEach(dataInitializer -> {
				try {
					dataInitializer.init();
				} catch (Exception e) {
					logger.info("系统数据初始化失败("+dataInitializer.getClass().getSimpleName()+")", e);
				}
			});
		}
	}

}
