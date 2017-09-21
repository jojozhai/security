/**
 * 
 */
package com.imooc.security.rbac.repository.support;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * <pre></pre>
 *
 * @author jojo 2014-2-17 下午2:29:16
 *
 */
public abstract class ImoocSpecification<T, C> extends AbstractEventConditionBuilder<T, C>
		implements Specification<T> {

	/**
	 * @param condition
	 */
	public ImoocSpecification(C condition) {
		super(condition);
	}

	/**
	 * 
	 * 构建查询条件，子类必须实现addCondition方法来编写查询的逻辑。
	 * 
	 * 子类可以通过addFetch方法控制查询的关联和抓取行为。
	 * 
	 */
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		if (Long.class != query.getResultType()) {
			addFetch(root);
		}

		List<Predicate> predicates = new ArrayList<Predicate>();

		QueryWraper<T> queryWraper = new QueryWraper<T>(root, query, cb, predicates);

		addCondition(queryWraper);

		Predicate permissionCondition = getPermissionCondition(queryWraper);
		if (permissionCondition != null) {
			queryWraper.addPredicate(permissionCondition);
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	/**
	 * 添加权限条件，如果要查询的domain实现了{@link ManagedByOrgan}接口，那么传入的Condition对象也应该实现
	 * {@link ManagedByOrgan}接口，
	 * 程序会尝试从Condition对象获取organFullId,然后作为like查询条件添加到查询中。
	 * 查出所有以传入的organFullId开头的domain.
	 * 
	 * @param queryWraper
	 * @return
	 */
	protected Predicate getPermissionCondition(QueryWraper<T> queryWraper) {
		return null;
	}

	/**
	 * <pre>
	 * 子类可以通过覆盖此方法实现关联抓取，避免n+1查询
	 * 
	 * <pre>
	 * 
	 * @param root
	 * @author jojo 2014-7-22 上午9:49:26
	 */
	protected void addFetch(Root<T> root) {
		
	}

	protected abstract void addCondition(QueryWraper<T> queryWraper);

}
