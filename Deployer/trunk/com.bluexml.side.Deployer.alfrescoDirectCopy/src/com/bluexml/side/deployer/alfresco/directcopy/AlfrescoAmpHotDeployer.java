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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfresco.webservice.authentication.AuthenticationFault;
import org.alfresco.webservice.content.Content;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLCreate;
import org.alfresco.webservice.types.ContentFormat;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.Node;
import org.alfresco.webservice.types.ParentReference;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.types.Store;
import org.alfresco.webservice.util.AuthenticationUtils;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.WebServiceFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom.Document;
import org.jdom.Element;

import com.bluexml.side.util.libs.xml.XmlHelper;

public class AlfrescoAmpHotDeployer extends AlfrescoAmpDirectDeployer {
	public static final String FORM_URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";

	// generation parameters
	private static final String ALFRESCO_ADMIN_PWD = "alfresco.admin.pwd";
	private static final String ALFRESCO_ADMIN_LOGIN = "alfresco.admin.login";
	public static final String CONFIGURATION_PARAMETER_ALFRESCO_URL = "alfresco.url";

	// options
	public static final String OPTION_HOT_DEPLOY = "com.bluexml.side.hotDeployment";
	// alfresco services URLs
	private static final String SERVICE_RESET_ON_SUBMIT_REFRESH_WEB_SCRIPTS = "/service/?reset=on&submit=Refresh%20Web%20Scripts";
	private static final String SERVICE_SIDE_WORKFLOWHOTDEPLOY_FILEPATH = "/service/side/workflowhotdeploy?filepath=";
	private static final String SERVICE_SIDE_SERVICE_MESSAGES_DEPLOY = "/service/side/messageshotdeploy?bundleBasePath=";
	private static final String SERVICE_SIDE_SERVICE_WEbCONFIG = "/service/side/webconfighotdeploy";

	// alfresco repository paths
	public static final String modelRepoPath = "/app:company_home/app:dictionary/app:models";
	public static final String messagesRepoPath = "/app:company_home/app:dictionary/app:messages";
	public static final String webclientRepoPath = "/app:company_home/app:dictionary/app:webclient_extension";

	private String getAdminPassWord() {
		return getGenerationParameters().get(ALFRESCO_ADMIN_PWD);
	}

	private String getAdminLogin() {
		return getGenerationParameters().get(ALFRESCO_ADMIN_LOGIN);
	}

	protected boolean doHotDeploy() {
		return options != null && options.contains(OPTION_HOT_DEPLOY);
	}
	
	@Override
	protected void postProcess(File fileToDeploy) {
		// implements hot deployment
		if (doHotDeploy()) {

			try {
				AuthenticationUtils.startSession(getAdminLogin(), getAdminPassWord());

				reloadDataTypeDefinition(); // upload

				reloadWorkflowDefinition();

				reloadWebScripts();

				reloadMessagesBundles();

				reloadWebConfig();

			} catch (AuthenticationFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			AuthenticationUtils.endSession();

		}
	}

	private void reloadWebConfig() throws AuthenticationException, Exception {
		System.out.println("AlfrescoAmpHotDeployer.reloadWebConfig()");
		String regex = ".*/module/([^/]*)/web-client-config-custom.xml";

		// search for messages
		List<String> list = new ArrayList<String>();
		// search for processDefinition files
		for (String string : deployedFiles) {

			boolean matches = string.matches(regex);

			if (matches) {
				list.add(string);
				System.out.println("web-client-config found :" + string);
			}
		}
		for (String filepath : list) {
			File model = new File(filepath);
			String moduleName = getModuleName(filepath);
			String prefix = moduleName + "_";
			String modelRepoName = prefix + model.getName();
			NamedValue[] props = new NamedValue[1];

			props[0] = new NamedValue("{http://www.alfresco.org/model/content/1.0}name", false, modelRepoName, null);
			ContentFormat format = new ContentFormat("text/plain", "UTF-8");

			uploadFile(model, modelRepoName, webclientRepoPath, props, format);
		}
		String alfrescoURL = getGenerationParameters().get(CONFIGURATION_PARAMETER_ALFRESCO_URL) + SERVICE_SIDE_SERVICE_WEbCONFIG;

		callReloadService(alfrescoURL);

	}

	private void reloadMessagesBundles() throws AuthenticationException, Exception {
		System.out.println("AlfrescoAmpHotDeployer.reloadMessagesBundles()");
		String regex = ".*WEB-INF/classes/(.*/module/[^/]*/[model|bpm])/.*properties";

		// search for messages
		List<String> list = new ArrayList<String>();
		// search for processDefinition files
		for (String string : deployedFiles) {

			boolean matches = string.matches(regex);

			if (matches) {
				list.add(string);
				System.out.println("model found :" + string);
			}
		}
		for (String filepath : list) {
			File model = new File(filepath);
			String classPath = getRegExG0(regex, filepath);
			// upload the file
			String prefix = getModuleName(filepath) + "_";
			String modelRepoName = prefix + model.getName();
			String[] split = modelRepoName.split("_");
			modelRepoName = "";
			for (String string : split) {
				modelRepoName += StringUtils.capitalize(string);
			}

			//			NamedValue[] props = new NamedValue[1];
			//			props[0] = new NamedValue("{http://www.alfresco.org/model/content/1.0}name", false, modelRepoName, null);
			//			ContentFormat format = new ContentFormat("text/plain", "UTF-8");
			//			uploadFile(model, modelRepoName, messagesRepoPath, props, format);

			// reload bundles
			String alfrescoURL = getGenerationParameters().get(CONFIGURATION_PARAMETER_ALFRESCO_URL) + SERVICE_SIDE_SERVICE_MESSAGES_DEPLOY + classPath;
			callReloadService(alfrescoURL);
		}
	}

	private String getModuleName(String path) {
		String regex = ".*/module/([^/]*)/.*";
		return getRegExG0(regex, path);
	}

	private static String getRegExG0(String regex, String txt) {
		String group = null;
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(txt);
		if (matcher.matches()) {
			group = matcher.group(1);
		}
		return group;
	}

	private void reloadDataTypeDefinition() throws IOException {
		System.out.println("AlfrescoAmpHotDeployer.reloadDataTypeDefinition()");
		List<String> list = new ArrayList<String>();
		// search for processDefinition files
		for (String string : deployedFiles) {
			String regex = ".*/module/[^/]*/model/[^.]*.xml";
			String regex2 = ".*/module/[^/]*/bpm/model.xml";
			boolean matches = string.matches(regex);
			boolean matches2 = string.matches(regex2);
			if (matches || matches2) {
				list.add(string);
				System.out.println("model found :" + string);
			}
		}
		for (String filepath : list) {
			File model = new File(filepath);

			String moduleName = model.getParentFile().getParentFile().getName();
			String prefix = moduleName + "_";
			String modelRepoName = prefix + model.getName();

			// validate the xml file against XSD ?

			// upload dataType definition into /Company Home/Data Dictionary/Models
			// open connection to Alfresco
			NamedValue[] props = new NamedValue[2];
			props[0] = new NamedValue("{http://www.alfresco.org/model/content/1.0}modelActive", false, "true", null);
			props[1] = new NamedValue("{http://www.alfresco.org/model/content/1.0}name", false, modelRepoName, null);
			ContentFormat format = new ContentFormat("text/xml", "UTF-8");
			uploadFile(model, modelRepoName, modelRepoPath, props, format);

		}
	}

	private void uploadFile(File model, String modelRepoName, String storepath, NamedValue[] props, ContentFormat format) throws IOException {

		Store store = new Store(Constants.WORKSPACE_STORE, "SpacesStore");

		ParentReference parentReference = new ParentReference(store, null, storepath, Constants.ASSOC_CONTAINS, Constants.createQNameString(Constants.NAMESPACE_CONTENT_MODEL, modelRepoName));

		// get the node
		String uuid = null;
		// model exists ?
		String modelPath = storepath + "/cm:" + modelRepoName;
		Reference r = new Reference(store, null, modelPath);
		System.out.println("search for :" + modelPath);
		try {
			Node[] n = WebServiceFactory.getRepositoryService().get(new Predicate(new Reference[] { r }, store, null));
			if (n.length > 0) {
				// node exist do update instead of create
				uuid = n[0].getReference().getUuid();
				System.out.println("Update model content in repository");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// node do not exists
			System.out.println("Create model in repository");

			CMLCreate create = new CMLCreate(null, parentReference, null, null, null, "{http://www.alfresco.org/model/content/1.0}content", props);
			CML cml = new CML();
			cml.setCreate(new CMLCreate[] { create });
			UpdateResult[] results = WebServiceFactory.getRepositoryService().update(cml);
			uuid = results[0].getDestination().getUuid();
		}

		// set node content

		// read xml file
		byte[] bytes = FileUtils.readFileToByteArray(model);

		// set content
		Content content = WebServiceFactory.getContentService().write(new Reference(store, uuid, null), Constants.PROP_CONTENT, bytes, format);
		System.out.println("model content updated " + content);

	}

	

	private void reloadWorkflowDefinition() throws AuthenticationException, Exception {
		// get the list of workflow process definition (search in deployed files)
		List<String> list = new ArrayList<String>();
		// search for processDefinition files
		for (String string : deployedFiles) {
			if (string.endsWith("processdefinition.xml")) {
				list.add(string);
				System.out.println("process :" + string);
			}
		}
		// now call service to deploy this definition
		String alfrescoURL = getGenerationParameters().get(CONFIGURATION_PARAMETER_ALFRESCO_URL) + SERVICE_SIDE_WORKFLOWHOTDEPLOY_FILEPATH;
		for (String string : list) {
			// compute the url
			String serializedDoc = callReloadService(alfrescoURL + new File(string).toURI());
			// search for error
			Document buildJdomDocument = XmlHelper.buildJdomDocument(serializedDoc);
			Element rootElement = buildJdomDocument.getRootElement();
			String textTrim = rootElement.getChild("defId").getTextTrim();

			List<?> children = rootElement.getChildren("problem");
			if (children.size() > 0) {
				String errorRepport = "Problem while load process definition : " + textTrim + "\n";
				for (Object object : children) {
					Element el = (Element) object;
					errorRepport += el.getTextTrim() + "\n";
				}
				throw new Exception(errorRepport);
			} else {
				// ok
				System.out.println("workflow process definition succesfully loaded id : " + textTrim);
			}
		}
	}

	private String callReloadService(String alfrescoURL) throws AuthenticationException, Exception {
		System.out.println("AlfrescoHotDeployer.callReloadService() : " + alfrescoURL);
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(alfrescoURL);

		Credentials defaultcreds = new UsernamePasswordCredentials(getAdminLogin(), getAdminPassWord());
		post.addHeader(new BasicScheme().authenticate(defaultcreds, post));

		post.addHeader("Content-Type", FORM_URL_ENCODED_CONTENT_TYPE + "; charset=UTF-8");
		String serializedDoc = AlfrescoHotDeployerHelper.executeRequest(httpclient, post);
		System.out.println(serializedDoc);
		return serializedDoc;
	}

	private void reloadWebScripts() throws AuthenticationException, Exception {
		String alfrescoURL = getGenerationParameters().get(CONFIGURATION_PARAMETER_ALFRESCO_URL);

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
