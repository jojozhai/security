/**
 * 
 */
package com.imooc.security.rbac.init;


/**
 * 数据初始化器，设计此接口的目的是封装系统数据的初始化行为，开发人员可以向系统中添加此接口的实现，来增加自定义的数据初始化行为.
 * 
 * @author zhailiang
 *
 */
public interface DataInitializer {

	/**
	 * 初始化器的执行顺序，数值越大的初始化器越靠后执行
	 * 
	 * @return
	 */
	Integer getIndex();

	/**
	 * 初始化数据的方法
	 * @throws Exception 
	 */
	void init() throws Exception;

}
