package com.bluexml.side.framework.alfresco.shareLanguagePicker.connector;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.extensions.config.RemoteConfigElement.ConnectorDescriptor;
import org.springframework.extensions.webscripts.connector.ConnectorContext;
import org.springframework.extensions.webscripts.connector.ConnectorSession;
import org.springframework.extensions.webscripts.connector.HttpConnector;
import org.springframework.extensions.webscripts.connector.Response;

import com.bluexml.side.framework.alfresco.shareLanguagePicker.LanguageSetter;

public class MyHttpConnector extends HttpConnector {

	public MyHttpConnector(ConnectorDescriptor descriptor, String endpoint) {
		super(descriptor, endpoint);
	}

	/* (non-Javadoc)
	 * @see org.springframework.extensions.webscripts.connector.HttpConnector#call(java.lang.String, org.springframework.extensions.webscripts.connector.ConnectorContext)
	 */
	@Override
	public Response call(String uri, ConnectorContext context) {
		// TODO Auto-generated method stub
		return super.call(uri, context);
	}

	/* (non-Javadoc)
	 * @see org.springframework.extensions.webscripts.connector.HttpConnector#call(java.lang.String, org.springframework.extensions.webscripts.connector.ConnectorContext, java.io.InputStream)
	 */
	@Override
	public Response call(String uri, ConnectorContext context, InputStream in) {
		// TODO Auto-generated method stub
		return super.call(uri, context, in);
	}

	/* (non-Javadoc)
	 * @see org.springframework.extensions.webscripts.connector.HttpConnector#call(java.lang.String, org.springframework.extensions.webscripts.connector.ConnectorContext, java.io.InputStream, java.io.OutputStream)
	 */
	@Override
	public Response call(String uri, ConnectorContext context, InputStream in, OutputStream out) {
		// TODO Auto-generated method stub
		return super.call(uri, context, in, out);
	}

	/* (non-Javadoc)
	 * @see org.springframework.extensions.webscripts.connector.AbstractConnector#call(java.lang.String)
	 */
	@Override
	public Response call(String uri) {
		// TODO Auto-generated method stub
		return super.call(uri);
	}

	/* (non-Javadoc)
	 * @see org.springframework.extensions.webscripts.connector.AbstractConnector#getConnectorSession()
	 */
	@Override
	public ConnectorSession getConnectorSession() {
		// TODO Auto-generated method stub
		return super.getConnectorSession();
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
