package com.bluexml.xforms.generator.forms.renderable.common.association;

import org.apache.commons.lang.StringUtils;

import com.bluexml.xforms.generator.forms.Renderable;
import com.bluexml.xforms.generator.forms.renderable.common.AssociationBean;

/**
 * The Class AbstractRenderable.
 */
public abstract class AbstractRenderable extends Renderable {

	/** The bean. */
	protected AssociationBean bean;

	/**
	 * Instantiates a new abstract renderable.
	 * 
	 * @param bean
	 *            the bean
	 */
	public AbstractRenderable(AssociationBean bean) {
		super();
		this.bean = bean;
	}

	/**
	 * Gets the bean.
	 * 
	 * @return the bean
	 */
	public AssociationBean getBean() {
		return bean;
	}

	/**
	 * Compute node set actions.
	 * 
	 * @param path
	 *            the path
	 * 
	 * @return the string
	 */
	protected String computeNodeSetActions(String path) {
		return StringUtils.removeEnd(path, "/");
	}

	/**
	 * Compute node set items.
	 * 
	 * @param path
	 *            the path
	 * @param nodeSetActions 
	 * 
	 * @return the string
	 */
	protected String computeNodeSetItems(String path, String nodeSetActions) {
		String nodeSetItems = nodeSetActions + "[position()!=last()]";
		return nodeSetItems;
	}

}
