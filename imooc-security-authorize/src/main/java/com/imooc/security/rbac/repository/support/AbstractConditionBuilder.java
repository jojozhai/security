package com.imooc.security.rbac.repository.support;

import java.util.Collection;
import java.util.Date;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

/**
 * <pre>
 *
 * <pre>
 * @author jojo 2014-8-12 下午2:57:22
 *
 */
public abstract class AbstractConditionBuilder<T> {
	
	/**
     * 添加in条件
     *
     * @param queryWraper
     * @param values
     */
    protected void addInConditionToColumn(QueryWraper<T> queryWraper, String column, Object values) {
        if (needAddCondition(values)) {
        	Path<?> fieldPath = getPath(queryWraper.getRoot(), column);
        	if(values.getClass().isArray()) {
        	    queryWraper.addPredicate(fieldPath.in((Object[])values));
            }else if(values instanceof Collection) {
                queryWraper.addPredicate(fieldPath.in((Collection<?>)values));
            }
        }
    }
    
    /**
     * 添加between条件查询
     * @param queryWraper
     * @param experssion
     * @param minValue  范围下限
     * @param maxValue  范围上限
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void addBetweenConditionToColumn(QueryWraper<T> queryWraper, String column, Comparable minValue, Comparable maxValue) {
    	if (minValue != null || maxValue != null) {
    		Path<? extends Comparable> fieldPath = getPath(queryWraper.getRoot(), column);
    		if(minValue != null && maxValue != null){
    			queryWraper.addPredicate(queryWraper.getCb().between(fieldPath, minValue, processMaxValueOnDate(maxValue)));
    		}else if(minValue != null){
    			queryWraper.addPredicate(queryWraper.getCb().greaterThanOrEqualTo(fieldPath, minValue));
    		}else if(maxValue != null){
    			queryWraper.addPredicate(queryWraper.getCb().lessThanOrEqualTo(fieldPath, processMaxValueOnDate(maxValue)));
    		}
    	}
    }

    /**
     * 当范围查询的条件是小于，并且值的类型是Date时，将传入的Date值变为当天的夜里12点的值。
     * @param maxValue
     * @return
     * @author zhailiang
     * @since 2016年12月14日
     */
    @SuppressWarnings("rawtypes")
    private Comparable processMaxValueOnDate(Comparable maxValue) {
        if(maxValue instanceof Date) {
            maxValue = new DateTime(maxValue).withTimeAtStartOfDay().plusDays(1).plusSeconds(-1).toDate();
        }
        return maxValue;
    }

    /**
     * 添加大于条件查询
     * @param queryWraper
     * @param experssion
     * @param minValue
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void addGreaterThanConditionToColumn(QueryWraper<T> queryWraper, String column,  Comparable minValue) {
    	if (minValue != null) {
    		Path<? extends Comparable> fieldPath = getPath(queryWraper.getRoot(), column);
    		queryWraper.addPredicate(queryWraper.getCb().greaterThan(fieldPath, minValue));
    	}
    }
	
	/**
     * 添加大于等于条件查询
     * @param queryWraper
     * @param experssion
     * @param minValue
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void addGreaterThanOrEqualConditionToColumn(QueryWraper<T> queryWraper, String column,  Comparable minValue) {
    	if (minValue != null) {
    		Path<? extends Comparable> fieldPath = getPath(queryWraper.getRoot(), column);
    		queryWraper.addPredicate(queryWraper.getCb().greaterThanOrEqualTo(fieldPath, minValue));
    	}
    }
	
	/**
     * 添加小于条件查询
     * @param queryWraper
     * @param experssion
     * @param maxValue
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void addLessThanConditionToColumn(QueryWraper<T> queryWraper, String column,  Comparable maxValue) {
    	if (maxValue != null) {
    		Path<? extends Comparable> fieldPath = getPath(queryWraper.getRoot(), column);
    		queryWraper.addPredicate(queryWraper.getCb().lessThan(fieldPath, processMaxValueOnDate(maxValue)));
    	}
    }

	/**
     * 添加小于等于条件查询
     * @param queryWraper
     * @param experssion
     * @param maxValue
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void addLessThanOrEqualConditionToColumn(QueryWraper<T> queryWraper, String column,  Comparable maxValue) {
    	if (maxValue != null) {
    		Path<? extends Comparable> fieldPath = getPath(queryWraper.getRoot(), column);
    		queryWraper.addPredicate(queryWraper.getCb().lessThanOrEqualTo(fieldPath, processMaxValueOnDate(maxValue)));
    	}
    }
	
	/**
	 * <pre>
	 * 添加like条件
	 * <pre>
	 * @param queryWraper
	 * @param column
	 * @param value
	 * @author jojo 2014-8-12 下午3:13:44
	 */
	protected void addLikeConditionToColumn(QueryWraper<T> queryWraper, String column, String value) {
		if (StringUtils.isNotBlank(value)) {
			queryWraper.addPredicate(createLikeCondition(queryWraper, column, value));
		}
	}
	
	/**
	 * @param queryWraper
	 * @param column
	 * @param value
	 * @return
	 * @author zhailiang
	 * @since 2016年12月13日
	 */
	@SuppressWarnings("unchecked")
	protected Predicate createLikeCondition(QueryWraper<T> queryWraper, String column, String value) {
        Path<String> fieldPath = getPath(queryWraper.getRoot(), column);
        Predicate condition = queryWraper.getCb().like(fieldPath, "%" + value + "%");
        return condition;
    }
	
	/**
	 * <pre>
	 * 添加like条件
	 * <pre>
	 * @param queryWraper
	 * @param column
	 * @param value
	 * @author jojo 2014-8-12 下午3:13:44
	 */
	@SuppressWarnings("unchecked")
	protected void addStartsWidthConditionToColumn(QueryWraper<T> queryWraper, String column, String value) {
		if (StringUtils.isNotBlank(value)) {
			Path<String> fieldPath = getPath(queryWraper.getRoot(), column);
			queryWraper.addPredicate(queryWraper.getCb().like(fieldPath,  value + "%"));
		}
	}


	/**
	 *  添加等于条件
	 * @param queryWraper
	 * @param column 指出要向哪个字段添加条件
	 * @param value 指定字段的值
	 */
	protected void addEqualsConditionToColumn(QueryWraper<T> queryWraper, String column, Object value) {
		if(needAddCondition(value)) {
			Path<?> fieldPath = getPath(queryWraper.getRoot(), column);
			queryWraper.addPredicate(queryWraper.getCb().equal(fieldPath, value));
		}
	}
	
	/**
	 *  添加不等于条件
	 * @param queryWraper
	 * @param column 指出要向哪个字段添加条件
	 * @param value 指定字段的值
	 */
	protected void addNotEqualsConditionToColumn(QueryWraper<T> queryWraper, String column, Object value) {
		if(needAddCondition(value)) {
			Path<?> fieldPath = getPath(queryWraper.getRoot(), column);
			queryWraper.addPredicate(queryWraper.getCb().notEqual(fieldPath, value));
		}
	}
	
	/**
	 * <pre>
	 *
	 * <pre>
	 * @param root
	 * @param property
	 * @return
	 * @author jojo 2014-8-12 下午3:06:58
	 */
	@SuppressWarnings("rawtypes")
	protected Path getPath(Root root, String property){
		String[] names = StringUtils.split(property, ".");
		Path path = root.get(names[0]);
		for (int i = 1; i < names.length; i++) {
			path = path.get(names[i]);
		}
		return path;
	}

	/**
	 * <pre>
	 * 判断是否需要添加where条件
	 * <pre>
	 * @param value
	 * @return
	 * @author jojo 2014-8-12 下午3:07:00
	 */
	@SuppressWarnings("rawtypes")
	protected boolean needAddCondition(Object value) {
		boolean addCondition = false;
		if (value != null) {
			if(value instanceof String) {
				if(StringUtils.isNotBlank(value.toString())) {
					addCondition = true;
				}
			}else if(value.getClass().isArray()) {
				if(ArrayUtils.isNotEmpty((Object[]) value)) {
					addCondition = true;
				}
			}else if(value instanceof Collection) {
				if(CollectionUtils.isNotEmpty((Collection) value)) {
					addCondition = true;
				}
			}else {
				addCondition = true;
			}
		}
		return addCondition;
	}

}
