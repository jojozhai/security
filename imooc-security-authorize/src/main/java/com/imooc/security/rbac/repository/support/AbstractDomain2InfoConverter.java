/**
 * 
 */
package com.imooc.security.rbac.repository.support;

import org.springframework.beans.BeanUtils;


/**
 * @author zhailiang
 *
 */
public abstract class AbstractDomain2InfoConverter<T, I> implements Domain2InfoConverter<T, I> {

	/* (non-Javadoc)
	 * @see com.idea.amp.core.support.dhtmlx.Domain2InfoConverter#convert(java.lang.Object, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public I convert(T domain) {
		I info = null;
		try {
			Class<I> clazz = GenericUtils.getGenericClass(getClass(), 1);
			info = clazz.newInstance();
			BeanUtils.copyProperties(domain, info);
			doConvert(domain, info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	protected abstract void doConvert(T domain, I info) throws Exception;

}
