package com.bluexml.side.portal.generator.alfresco.services;
import com.bluexml.side.clazz.service.alfresco.CommonServices;
import com.bluexml.side.common.NamedModelElement;
public class ProxieServices {

	/**
	 * method proxie to access to CommonServices.getNamedModelElementQName
	 * @param node
	 * @return
	 */
	public static String getNamedModelElementQName(NamedModelElement node) {
		return CommonServices.getNamedModelElementQName(node);
	}
}
