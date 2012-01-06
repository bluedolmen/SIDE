/**
 * 
 */
package com.bluexml.xforms.controller.beans;

import com.bluexml.xforms.controller.binding.GenericClass;

/**
 * Bean for the result of a request for form persistence.
 * 
 * @author Amenel
 * 
 */
public class PersistFormResultBean {
	String resultStr; // for default forms and FormClass's
	GenericClass resultClass; // for worfklow forms

	/**
	 * 
	 */
	public PersistFormResultBean() {
		super();
		resultStr = null;
		resultClass = null;
	}

	/**
	 * @return the resultStr
	 */
	public String getResultStr() {
		return resultStr;
	}

	/**
	 * @param resultStr
	 *            the resultStr to set
	 */
	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	/**
	 * @return the resultClass
	 */
	public GenericClass getResultClass() {
		return resultClass;
	}

	/**
	 * @param resultClass
	 *            the resultClass to set
	 */
	public void setResultClass(GenericClass resultClass) {
		this.resultClass = resultClass;
	}

}
