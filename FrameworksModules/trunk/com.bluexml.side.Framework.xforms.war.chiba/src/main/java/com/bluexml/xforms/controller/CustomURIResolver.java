package com.bluexml.xforms.controller;

import org.chiba.xml.xforms.connector.AbstractConnector;
import org.chiba.xml.xforms.connector.URIResolver;
import org.chiba.xml.xforms.exception.XFormsException;
import org.w3c.dom.Node;

import com.bluexml.xforms.controller.navigation.NavigationManager;

/**
 * The Class CustomURIResolver.<br />
 * Retrieve XForms instance thanks to a custom uri
 */
public class CustomURIResolver extends AbstractConnector implements URIResolver {

	/* (non-Javadoc)
	 * @see org.chiba.xml.xforms.connector.URIResolver#resolve()
	 */
	public Object resolve() throws XFormsException {
		Node document = null;
		try {
			document = NavigationManager.getInstance().xformsResolve(getURI());
		} catch (Exception e) {
			throw new XFormsException(e);
		}
		return document;
	}
}
