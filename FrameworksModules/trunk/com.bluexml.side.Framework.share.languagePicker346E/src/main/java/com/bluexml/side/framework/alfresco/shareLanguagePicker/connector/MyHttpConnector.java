/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
