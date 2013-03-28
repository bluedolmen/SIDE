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
package com.bluexml.side.deployer.alfresco.directcopy;

import java.io.File;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import com.bluexml.side.util.deployer.war.DirectWebAppsDeployer;

public class AlfrescoShareDirectDeployer extends DirectWebAppsDeployer {
	public static final String FORM_URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";

	private static final String SERVICE_RESET_ON_SUBMIT_REFRESH_WEB_SCRIPTS = "/service/index?reset=on&submit=Refresh%20Web%20Scripts";
	public static final String CONFIGURATION_PARAMETER_SHARE_URL = "share.url";
	// generation parameters
	private static final String ALFRESCO_ADMIN_PWD = "alfresco.admin.pwd";
	private static final String ALFRESCO_ADMIN_LOGIN = "alfresco.admin.login";

	// options
	public static final String OPTION_HOT_DEPLOY = "com.bluexml.side.hotDeployment";

	public AlfrescoShareDirectDeployer() {
		super(null, "share", "deployer.webappName.alfrescoshare", "zip");
	}

	protected boolean doHotDeploy() {
		return options != null && options.contains(OPTION_HOT_DEPLOY);
	}

	@Override
	protected void postProcess(File fileToDeploy) throws Exception {
		System.out.println("AlfrescoShareDirectDeployer.postProcess() hotdeploy ?:" + doHotDeploy());
		if (doHotDeploy()) {
			try {
				reloadWebScripts();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String getAdminPassWord() {
		return getGenerationParameters().get(ALFRESCO_ADMIN_PWD);
	}

	private String getAdminLogin() {
		return getGenerationParameters().get(ALFRESCO_ADMIN_LOGIN);
	}

	private void reloadWebScripts() throws AuthenticationException, Exception {
		System.out.println("AlfrescoShareDirectDeployer.reloadWebScripts()");

		String alfrescoURL = getGenerationParameters().get(CONFIGURATION_PARAMETER_SHARE_URL);

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(alfrescoURL + SERVICE_RESET_ON_SUBMIT_REFRESH_WEB_SCRIPTS);

		Credentials defaultcreds = new UsernamePasswordCredentials(getAdminLogin(), getAdminPassWord());
		post.addHeader(new BasicScheme().authenticate(defaultcreds, post));

		post.addHeader("Content-Type", FORM_URL_ENCODED_CONTENT_TYPE + "; charset=UTF-8");
		String executeRequest = AlfrescoHotDeployerHelper.executeRequest(httpclient, post);

		// search for error

		System.out.println(executeRequest);
	}

}
