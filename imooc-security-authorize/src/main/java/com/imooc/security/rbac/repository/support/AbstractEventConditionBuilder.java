package com.imooc.security.rbac.repository.support;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * <pre>
 *
 * <pre>
 * @author jojo 2014-8-12 下午2:58:10
 *
 */
public abstract class AbstractEventConditionBuilder<T, C> extends AbstractConditionBuilder<T> {

	/**
	 * 查询条件
	 */
	private C condition;
	
	/**
	 * @param condition 查询条件
	 */
	public AbstractEventConditionBuilder(C condition){
		this.condition = condition;
	}

	/**
	 * 向查询中添加包含(like)条件
	 * 
	 * @param queryWraper
	 * @param field 指出查询条件的值从condition对象的哪个字段里取，并且指出要向哪个字段添加包含(like)条件。(同一个字段名称)
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void addLikeCondition(QueryWraper<T> queryWraper, String field){
		addLikeCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加包含(like)条件
	 * 
	 * @param queryWraper
	 * @param field 指出查询条件的值从condition对象的哪个字段里取
	 * @param column 指出要向哪个字段添加包含(like)条件
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void addLikeCondition(QueryWraper<T> queryWraper, String field, String column){
		addLikeConditionToColumn(queryWraper, column, (String) 
				getValue(getCondition(), field));
	}
	
	
	/**
	 * 向查询中添加包含(like)条件,%放在值后面
	 * 
	 * @param queryWraper
	 * @param field 指出查询条件的值从condition对象的哪个字段里取，并且指出要向哪个字段添加包含(like)条件。(同一个字段名称)
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void addStartsWidthCondition(QueryWraper<T> queryWraper, String field){
		addStartsWidthCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加包含(like)条件,%放在值后面
	 * 
	 * @param queryWraper
	 * @param field 指出查询条件的值从condition对象的哪个字段里取
	 * @param column 指出要向哪个字段添加包含(like)条件
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void addStartsWidthCondition(QueryWraper<T> queryWraper, String field, String column){
		addStartsWidthConditionToColumn(queryWraper, column, (String) 
				getValue(getCondition(), field));
	}
		
	/**
	 * 向查询中添加等于(=)条件
	 * 
	 * @param queryWraper
	 * @param field 指出查询条件的值从condition对象的哪个字段里取，并且指出要向哪个字段添加条件。(同一个字段名称)
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void addEqualsCondition(QueryWraper<T> queryWraper, String field){
		addEqualsCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加等于(=)条件
	 * 
	 * @param queryWraper
	 * @param field 指出查询条件的值从condition对象的哪个字段里取
	 * @param column 指出要向哪个字段添加条件
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void addEqualsCondition(QueryWraper<T> queryWraper, String field, String column){
		addEqualsConditionToColumn(queryWraper, column, 
				getValue(getCondition(), field));
	}
	
	/**
	 * 向查询中添加不等于(!=)条件
	 * 
	 * @param queryWraper
	 * @param field 指出查询条件的值从condition对象的哪个字段里取，并且指出要向哪个字段添加条件。(同一个字段名称)
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void addNotEqualsCondition(QueryWraper<T> queryWraper, String field){
		addNotEqualsCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加等于(=)条件
	 * 
	 * @param queryWraper
	 * @param field 指出查询条件的值从condition对象的哪个字段里取
	 * @param column 指出要向哪个字段添加条件
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void addNotEqualsCondition(QueryWraper<T> queryWraper, String field, String column){
		addNotEqualsConditionToColumn(queryWraper, column, getValue(getCondition(), field));
	}
	
	/**
	 * <pre>
	 * 向查询中添加in条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @author jojo 2014-8-12 下午3:26:50
	 */
	protected void addInCondition(QueryWraper<T> queryWraper, String field) {
		addInCondition(queryWraper, field, field);
	}
	/**
	 * <pre>
	 * 向查询中添加in条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @param column
	 * @author jojo 2014-8-12 下午3:27:46
	 */
	protected void addInCondition(QueryWraper<T> queryWraper, String field, String column) {
		addInConditionToColumn(queryWraper, column, 
				getValue(getCondition(), field));
	}
	
	/**
	 * <pre>
	 * 向查询中添加between条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @author jojo 2014-8-12 下午3:26:50
	 */
	protected void addBetweenCondition(QueryWraper<T> queryWraper, String field) {
		addBetweenCondition(queryWraper, field, field+"To", field);
	}
	/**
	 * <pre>
	 * 向查询中添加between条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @param column
	 * @author jojo 2014-8-12 下午3:27:46
	 */
	@SuppressWarnings("rawtypes")
	protected void addBetweenCondition(QueryWraper<T> queryWraper, String startField, String endField, String column) {
		addBetweenConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), startField), 
				(Comparable)getValue(getCondition(), endField));
	}
	
	/**
	 * <pre>
	 * 向查询中添加大于条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @author jojo 2014-8-12 下午3:26:50
	 */
	protected void addGreaterThanCondition(QueryWraper<T> queryWraper, String field) {
		addGreaterThanCondition(queryWraper, field, field);
	}
	/**
	 * <pre>
	 * 向查询中添加大于条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @param column
	 * @author jojo 2014-8-12 下午3:27:46
	 */
	@SuppressWarnings("rawtypes")
	protected void addGreaterThanCondition(QueryWraper<T> queryWraper, String field, String column) {
		addGreaterThanConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), field));
	}
	
	/**
	 * <pre>
	 * 向查询中添加大于等于条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @author jojo 2014-8-12 下午3:26:50
	 */
	protected void addGreaterThanOrEqualCondition(QueryWraper<T> queryWraper, String field) {
		addGreaterThanOrEqualCondition(queryWraper, field, field);
	}
	
	/**
	 * <pre>
	 * 向查询中添加大于等于条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @param column
	 * @author jojo 2014-8-12 下午3:27:46
	 */
	@SuppressWarnings("rawtypes")
	protected void addGreaterThanOrEqualCondition(QueryWraper<T> queryWraper, String field, String column) {
		addGreaterThanOrEqualConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), field));
	}
	
	/**
	 * <pre>
	 * 向查询中添加小于条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @author jojo 2014-8-12 下午3:26:50
	 */
	protected void addLessThanCondition(QueryWraper<T> queryWraper, String field) {
		addLessThanCondition(queryWraper, field, field);
	}
	/**
	 * <pre>
	 * 向查询中添加小于条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @param column
	 * @author jojo 2014-8-12 下午3:27:46
	 */
	@SuppressWarnings("rawtypes")
	protected void addLessThanCondition(QueryWraper<T> queryWraper, String field, String column) {
		addLessThanConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), field));
	}

	/**
	 * <pre>
	 * 向查询中添加小于等于条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @author jojo 2014-8-12 下午3:26:50
	 */
	protected void addLessThanOrEqualCondition(QueryWraper<T> queryWraper, String field) {
		addLessThanOrEqualCondition(queryWraper, field, field);
	}
	
	/**
	 * <pre>
	 * 向查询中添加小于等于条件
	 * <pre>
	 * @param queryWraper
	 * @param field
	 * @param column
	 * @author jojo 2014-8-12 下午3:27:46
	 */
	@SuppressWarnings("rawtypes")
	protected void addLessThanOrEqualCondition(QueryWraper<T> queryWraper, String field, String column) {
		addLessThanOrEqualConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), field));
	}

	private Object getValue(C condition, String field) {
		try {
			return PropertyUtils.getProperty(condition, field);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return the condition
	 */
	public C getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(C condition) {
		this.condition = condition;
	}
	
}
