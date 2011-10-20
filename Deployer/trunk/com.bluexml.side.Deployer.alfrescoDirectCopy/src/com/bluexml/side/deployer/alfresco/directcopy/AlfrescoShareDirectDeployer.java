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
			reloadWebScripts();
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
