package com.bluexml.side.framework.alfresco.shareLanguagePicker.connector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.extensions.config.RemoteConfigElement.ConnectorDescriptor;
import org.springframework.extensions.webscripts.connector.ConnectorContext;
import org.springframework.extensions.webscripts.connector.HttpConnector;
import org.springframework.extensions.webscripts.connector.Response;

import com.bluexml.side.framework.alfresco.shareLanguagePicker.LanguageSetter;

public class MyHttpConnector extends HttpConnector {

	public MyHttpConnector(ConnectorDescriptor descriptor, String endpoint) {
		super(descriptor, endpoint);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.extensions.webscripts.connector.HttpConnector#call
	 * (java.lang.String,
	 * org.springframework.extensions.webscripts.connector.ConnectorContext,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Response call(String uri, ConnectorContext context, HttpServletRequest req, HttpServletResponse res) {
		HttpServletRequest myreq = LanguageSetter.getMyRequestInstance(req);
		return super.call(uri, context, myreq, res);
	}

	

}
