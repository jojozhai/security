/**
 * 
 */
package com.imooc.security.rbac.repository.support;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 包装用于构建JPA动态查询时所需的对象
 * 
 * @author jojo 2013-7-4 下午2:57:51
 */
public class QueryWraper<T> {

	/**
	 * @param root
	 *            JPA Root
	 * @param cb
	 *            JPA CriteriaBuilder
	 * @param predicates
	 *            JPA Predicate 集合
	 */
	public QueryWraper(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<Predicate> predicates) {
		this.root = root;
		this.query = query;
		this.cb = cb;
		this.predicates = predicates;
	}

	/**
	 * JPA Root
	 */
	private Root<T> root;
	/**
	 * JPA CriteriaBuilder
	 */
	private CriteriaBuilder cb;
	/**
	 * JPA Predicate 集合
	 */
	private List<Predicate> predicates;
	/**
	 * JPA 查询对象
	 */
	private CriteriaQuery<?> query;

	/**
	 * <pre>
	 *
	 * <pre>
	 * @param predicate
	 * @author jojo 2014-8-12 下午3:12:36
	 */
	public void addPredicate(Predicate predicate) {
		this.predicates.add(predicate);
	}
	/**
	 * @return the root
	 */
	public Root<T> getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(Root<T> root) {
		this.root = root;
	}

	/**
	 * @return the cb
	 */
	public CriteriaBuilder getCb() {
		return cb;
	}

	/**
	 * @param cb
	 *            the cb to set
	 */
	public void setCb(CriteriaBuilder cb) {
		this.cb = cb;
	}

	/**
	 * @return the predicates
	 */
	public List<Predicate> getPredicates() {
		return predicates;
	}

	/**
	 * @param predicates
	 *            the predicates to set
	 */
	public void setPredicates(List<Predicate> predicates) {
		this.predicates = predicates;
	}

	/**
	 * @return the query
	 */
	public CriteriaQuery<?> getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(CriteriaQuery<?> query) {
		this.query = query;
	}

}
